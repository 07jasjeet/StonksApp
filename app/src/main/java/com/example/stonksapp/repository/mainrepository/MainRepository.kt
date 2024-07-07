package com.example.stonksapp.repository.mainrepository

import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.data.TickerSearchResult
import com.example.stonksapp.data.TopGainersLosers
import com.example.stonksapp.utils.Resource

interface MainRepository {

    suspend fun searchSymbol(
        keywords: String
    ): Resource<TickerSearchResult>

    suspend fun fetchCompanyOverview(symbol: String): Resource<CompanyOverview>

    suspend fun fetchTopGainersLosers(): Resource<TopGainersLosers>
}