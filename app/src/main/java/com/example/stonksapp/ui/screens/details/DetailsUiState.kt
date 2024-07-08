package com.example.stonksapp.ui.screens.details

import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.utils.ResponseError

data class DetailsUiState(
    val resource: Resource<CompanyOverview> = Resource.loading(),
    val error: ResponseError? = null
)