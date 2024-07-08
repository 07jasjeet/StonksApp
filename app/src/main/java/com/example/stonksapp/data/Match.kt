package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName


data class Match (

  @SerializedName("1. symbol"      ) val symbol      : String,
  @SerializedName("2. name"        ) val name        : String,
  @SerializedName("3. type"        ) val type        : String,
  @SerializedName("4. region"      ) val region      : String,
  @SerializedName("5. marketOpen"  ) val marketOpen  : String,
  @SerializedName("6. marketClose" ) val marketClose : String,
  @SerializedName("7. timezone"    ) val timezone    : String,
  @SerializedName("8. currency"    ) val currency    : String,
  @SerializedName("9. matchScore"  ) val matchScore  : String

) {
  constructor(m: Mock): this(
    symbol = "TSCO.LON",
    name = "Tesco PLC",
    type = "Equity",
    region = "United Kingdom",
    marketOpen = "08:00",
    marketClose = "16:30",
    timezone = "UTC+01",
    currency = "GBX",
    matchScore = "0.7273"
  )
}