package com.example.stonksapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.stonksapp.data.StockOverview
import com.example.stonksapp.data.TopGainersLosers
import com.example.stonksapp.repository.mainrepository.MainRepository
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.utils.ResponseError
import com.example.stonksapp.utils.processRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: MainRepository
): BaseViewModel<ExploreUiState>() {
    private val topGainersLosersFlow = MutableStateFlow<Resource<TopGainersLosers>>(Resource.loading())

    override val uiState: StateFlow<ExploreUiState> = createUiStateFlow()

    init {
        fetchExploreData()
    }

    override fun createUiStateFlow(): StateFlow<ExploreUiState> =
        combine(
            topGainersLosersFlow,
            errorFlow
        ) { data, error ->
            ExploreUiState(
                topGainersLosers = data,
                error = error
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ExploreUiState()
        )

    fun fetchExploreData() {
        viewModelScope.launch {
            topGainersLosersFlow.processRequest {
                repository.fetchTopGainersLosers()
            }.emitErrorIfAny()
        }
    }
}

data class ExploreUiState(
    val topGainersLosers: Resource<TopGainersLosers> = Resource.loading(),
    val error: ResponseError? = null
)