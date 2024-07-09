package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName


data class TopGainersLosers (

  @SerializedName("metadata"             ) val metadata              : String?                       = null,
  @SerializedName("last_updated"         ) val lastUpdated           : String?                       = null,
  @SerializedName("top_gainers"          ) val topGainers            : List<StockOverview>           = arrayListOf(),
  @SerializedName("top_losers"           ) val topLosers             : List<StockOverview>           = arrayListOf(),
  @SerializedName("most_actively_traded" ) val mostActivelyTraded    : List<StockOverview>           = arrayListOf(),
  @SerializedName(ERROR_MESSAGE          ) override val errorMessage : String?                       = null,
  @SerializedName(INFORMATION            ) override val information  : String?                       = null

): AlphaVantageResponse() {
  constructor(m: Mock): this(
    topGainers = List(20) {
      StockOverview(Mock)
    },
    topLosers = List(20) {
      StockOverview(Mock)
    }
  )
}