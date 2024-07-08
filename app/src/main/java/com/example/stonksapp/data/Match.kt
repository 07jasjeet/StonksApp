package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName


data class Match (

  @SerializedName("1. symbol"      ) val symbol      : String? = null,
  @SerializedName("2. name"        ) val name        : String? = null,
  @SerializedName("3. type"        ) val type        : String? = null,
  @SerializedName("4. region"      ) val region      : String? = null,
  @SerializedName("5. marketOpen"  ) val marketOpen  : String? = null,
  @SerializedName("6. marketClose" ) val marketClose : String? = null,
  @SerializedName("7. timezone"    ) val timezone    : String? = null,
  @SerializedName("8. currency"    ) val currency    : String? = null,
  @SerializedName("9. matchScore"  ) val matchScore  : String? = null

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