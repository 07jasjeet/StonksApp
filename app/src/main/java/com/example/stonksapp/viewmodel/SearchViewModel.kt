package com.example.stonksapp.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stonksapp.data.Match
import com.example.stonksapp.data.TickerSearchResult
import com.example.stonksapp.di.DefaultDispatcher
import com.example.stonksapp.di.IoDispatcher
import com.example.stonksapp.repository.mainrepository.MainRepository
import com.example.stonksapp.ui.screens.search.SearchUiState
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.utils.ResponseError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel<SearchUiState>() {

    private val inputQueryFlow = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val queryFlow = inputQueryFlow
        .asStateFlow()
        .debounce(800)
        .distinctUntilChanged()

    // Result flows
    private val resultFlow = MutableStateFlow<List<Match>>(emptyList())

    override val uiState: StateFlow<SearchUiState> = createUiStateFlow()

    init {
        // Engage query flow
        viewModelScope.launch(ioDispatcher) {
            queryFlow.collectLatest { keywords ->
                if (keywords.isEmpty()){
                    resultFlow.emit(emptyList())
                    return@collectLatest
                }

                val result = repository.searchSymbol(keywords)
                when (result.status) {
                    Resource.Status.SUCCESS -> resultFlow.emit(result.data?.bestMatches ?: emptyList())
                    Resource.Status.FAILED -> emitError(result.error)
                    else -> return@collectLatest
                }
            }
        }
    }

    override fun createUiStateFlow(): StateFlow<SearchUiState> {
        return combine(
            inputQueryFlow,
            resultFlow,
            errorFlow
        ){ query: String, matches: List<Match>, error: ResponseError? ->
            return@combine SearchUiState(query, matches, error)
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            SearchUiState("", listOf(), null)
        )
    }


    fun updateQueryFlow(query: String) {
        viewModelScope.launch {
            inputQueryFlow.emit(query)
        }
    }


    fun clearUi() {
        viewModelScope.launch {
            resultFlow.emit(emptyList())
            inputQueryFlow.emit("")
        }
    }
}