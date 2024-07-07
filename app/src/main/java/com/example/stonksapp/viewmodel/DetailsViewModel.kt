package com.example.stonksapp.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.repository.mainrepository.MainRepository
import com.example.stonksapp.ui.screens.details.DetailsUiState
import com.example.stonksapp.utils.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.ViewModelFactory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val symbol: String,
    private val repository: MainRepository
): BaseViewModel<DetailsUiState>() {

    @AssistedFactory
    interface ViewModelFactory {
        fun create(symbol: String): DetailsViewModel
    }

    private val isLoadingFlow = MutableStateFlow(true)
    private val resultFlow = MutableStateFlow<CompanyOverview?>(null)

    init {
        fetchDetails(symbol)
    }

    override val uiState: StateFlow<DetailsUiState> = createUiStateFlow()

    override fun createUiStateFlow(): StateFlow<DetailsUiState> =
        combine(
            isLoadingFlow,
            resultFlow,
            errorFlow
        ) { isLoading, result, error ->
            DetailsUiState(
                isLoading = isLoading,
                companyOverview = result,
                error = error
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            DetailsUiState()
        )

    fun fetchDetails(symbol: String) {
        viewModelScope.launch {
            isLoadingFlow.emit(true)
            val result = repository.fetchCompanyOverview(symbol)
            when (result.status) {
                Resource.Status.SUCCESS -> resultFlow.emit(result.data)
                Resource.Status.FAILED -> emitError(result.error)
                else -> Unit
            }
            isLoadingFlow.emit(false)
        }
    }
}