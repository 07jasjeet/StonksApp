package com.example.stonksapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.stonksapp.data.StockOverview
import com.example.stonksapp.repository.mainrepository.MainRepository
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.utils.ResponseError
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
    private val isLoadingFlow = MutableStateFlow(false)
    private val topGainersFlow = MutableStateFlow<List<StockOverview>>(listOf())
    private val topLosersFlow = MutableStateFlow<List<StockOverview>>(listOf())

    override val uiState: StateFlow<ExploreUiState> = createUiStateFlow()

    init {
        fetchExploreData()
    }

    override fun createUiStateFlow(): StateFlow<ExploreUiState> =
        combine(
            isLoadingFlow,
            topGainersFlow,
            topLosersFlow,
            errorFlow
        ) { isLoading, topGainers, topLosers, error ->
            ExploreUiState(
                isLoading = isLoading,
                topGainers = topGainers,
                topLosers = topLosers,
                error = error
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ExploreUiState()
        )

    fun fetchExploreData() {
        viewModelScope.launch {
            isLoadingFlow.emit(true)
            val result = repository.fetchTopGainersLosers()
            Log.d("ExploreViewModel", "fetchExploreData: ${result}")
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    topGainersFlow.emit(result.data?.topGainers ?: emptyList())
                    topLosersFlow.emit(result.data?.topLosers ?: emptyList())
                }
                Resource.Status.FAILED -> emitError(result.error)
                else -> Unit
            }
            isLoadingFlow.emit(false)
        }
    }
}

data class ExploreUiState(
    val isLoading: Boolean = true,
    val topGainers: List<StockOverview> = listOf(),
    val topLosers: List<StockOverview> = listOf(),
    val error: ResponseError? = null
)