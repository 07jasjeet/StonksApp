package com.example.stonksapp.data

import com.google.gson.annotations.SerializedName


data class StockOverview (

  @SerializedName("ticker"            ) val ticker           : String? = null,
  @SerializedName("price"             ) val price            : String? = null,
  @SerializedName("change_amount"     ) val changeAmount     : String? = null,
  @SerializedName("change_percentage" ) val changePercentage : String? = null,
  @SerializedName("volume"            ) val volume           : String? = null

)