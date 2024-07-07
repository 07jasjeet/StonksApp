package com.example.stonksapp.data

import com.google.gson.annotations.SerializedName


data class TickerSearchResult (

  @SerializedName("bestMatches"  ) val bestMatches          : ArrayList<Match> = arrayListOf(),
  @SerializedName(ERROR_MESSAGE  ) override val errorMessage       : String? = null,
  @SerializedName(INFORMATION    ) override val information : String? = null

): AlphaVantageResponse()