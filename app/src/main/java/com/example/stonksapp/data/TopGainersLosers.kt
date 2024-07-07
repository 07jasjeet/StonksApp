package com.example.stonksapp.data

import com.google.gson.annotations.SerializedName


data class TopGainersLosers (

  @SerializedName("metadata"             ) val metadata              : String?                       = null,
  @SerializedName("last_updated"         ) val lastUpdated           : String?                       = null,
  @SerializedName("top_gainers"          ) val topGainers            : ArrayList<StockOverview>      = arrayListOf(),
  @SerializedName("top_losers"           ) val topLosers             : ArrayList<StockOverview>      = arrayListOf(),
  @SerializedName("most_actively_traded" ) val mostActivelyTraded    : ArrayList<StockOverview>      = arrayListOf(),
  @SerializedName(ERROR_MESSAGE          ) override val errorMessage : String?                       = null,
  @SerializedName(INFORMATION            ) override val information  : String?                       = null

): AlphaVantageResponse()