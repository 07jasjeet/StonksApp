package com.example.stonksapp.data

import com.google.gson.annotations.SerializedName


data class StockOverview (

  @SerializedName("ticker"            ) val ticker           : String,
  @SerializedName("price"             ) val price            : String,
  @SerializedName("change_amount"     ) val changeAmount     : String,
  @SerializedName("change_percentage" ) val changePercentage : String,
  @SerializedName("volume"            ) val volume           : String

)

enum class StockType {
  Gainer,
  Loser
}