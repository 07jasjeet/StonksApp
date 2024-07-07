package com.example.stonksapp.ui.screens.details

import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.utils.ResponseError

data class DetailsUiState(
    val isLoading: Boolean = true,
    val companyOverview: CompanyOverview? = null,
    val error: ResponseError? = null
)