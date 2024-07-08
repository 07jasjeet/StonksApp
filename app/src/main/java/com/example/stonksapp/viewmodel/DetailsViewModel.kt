package com.example.stonksapp.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.repository.mainrepository.MainRepository
import com.example.stonksapp.ui.screens.details.DetailsUiState
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.utils.processRequest
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

    private val resultFlow = MutableStateFlow<Resource<CompanyOverview>>(Resource.loading())

    init {
        fetchDetails(symbol)
    }

    override val uiState: StateFlow<DetailsUiState> = createUiStateFlow()

    override fun createUiStateFlow(): StateFlow<DetailsUiState> =
        combine(
            resultFlow,
            errorFlow
        ) {  result, error ->
            DetailsUiState(
                resource = result,
                error = error
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            DetailsUiState()
        )

    fun fetchDetails(symbol: String) {
        viewModelScope.launch {
            resultFlow.processRequest {
                repository.fetchCompanyOverview(symbol)
            }.emitErrorIfAny()
        }
    }
}