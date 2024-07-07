package com.example.stonksapp.service

import com.example.stonksapp.data.TopGainersLosers
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.data.TickerSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("query?function=${Functions.OVERVIEW}")
    suspend fun getCompanyOverview(
        @Query("symbol") symbol: String,
    ): Response<CompanyOverview>

    @GET("query?function=${Functions.SYMBOL_SEARCH}")
    suspend fun searchSymbol(
        @Query("keywords") keywords: String,
        @Query("datatype") datatype: String = DataType.JSON,
    ): Response<TickerSearchResult>

    @GET("query?function=${Functions.TOP_GAINERS_LOSERS}")
    suspend fun getTopGainersLosers(): Response<TopGainersLosers>

    companion object {
        const val BASE_URL = "https://www.alphavantage.co/"
        object Functions {
            const val OVERVIEW = "OVERVIEW"
            const val SYMBOL_SEARCH = "SYMBOL_SEARCH"
            const val TOP_GAINERS_LOSERS = "TOP_GAINERS_LOSERS"
        }
        object DataType {
            const val JSON = "json"
            const val CSV = "csv"
        }
    }
}