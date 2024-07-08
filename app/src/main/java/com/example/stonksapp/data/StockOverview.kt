package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName


data class StockOverview (

  @SerializedName("ticker"            ) val ticker           : String,
  @SerializedName("price"             ) val price            : String,
  @SerializedName("change_amount"     ) val changeAmount     : String,
  @SerializedName("change_percentage" ) val changePercentage : String,
  @SerializedName("volume"            ) val volume           : String

) {
  constructor(m: Mock): this(
    ticker = "SHOTW",
    price = "0.34",
    changeAmount = "0.16",
    changePercentage = "88.8889%",
    volume = "1597"
  )
}

enum class StockType {
  Gainer,
  Loser
}