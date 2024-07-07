package com.example.stonksapp.repository.mainrepository

import com.example.stonksapp.service.ApiService
import com.example.stonksapp.utils.parseResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): MainRepository {

    override suspend fun searchSymbol(keywords: String) = parseResponse {
        apiService.searchSymbol(keywords)
    }

    override suspend fun fetchCompanyOverview(symbol: String) = parseResponse {
        apiService.getCompanyOverview(symbol)
    }

    override suspend fun fetchTopGainersLosers() = parseResponse {
        apiService.getTopGainersLosers()
    }
}