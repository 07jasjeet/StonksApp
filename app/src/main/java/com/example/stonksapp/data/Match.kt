package com.example.stonksapp.data

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

)