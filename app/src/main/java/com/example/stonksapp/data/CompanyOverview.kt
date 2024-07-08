package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName

data class CompanyOverview (

    @SerializedName("Symbol"                     ) val Symbol                     : String? = null,
    @SerializedName("AssetType"                  ) val AssetType                  : String? = null,
    @SerializedName("Name"                       ) val Name                       : String? = null,
    @SerializedName("Description"                ) val Description                : String? = null,
    @SerializedName("CIK"                        ) val CIK                        : String? = null,
    @SerializedName("Exchange"                   ) val Exchange                   : String? = null,
    @SerializedName("Currency"                   ) val Currency                   : String? = null,
    @SerializedName("Country"                    ) val Country                    : String? = null,
    @SerializedName("Sector"                     ) val Sector                     : String? = null,
    @SerializedName("Industry"                   ) val Industry                   : String? = null,
    @SerializedName("Address"                    ) val Address                    : String? = null,
    @SerializedName("FiscalYearEnd"              ) val FiscalYearEnd              : String? = null,
    @SerializedName("LatestQuarter"              ) val LatestQuarter              : String? = null,
    @SerializedName("MarketCapitalization"       ) val MarketCapitalization       : String? = null,
    @SerializedName("EBITDA"                     ) val EBITDA                     : String? = null,
    @SerializedName("PERatio"                    ) val PERatio                    : String? = null,
    @SerializedName("PEGRatio"                   ) val PEGRatio                   : String? = null,
    @SerializedName("BookValue"                  ) val BookValue                  : String? = null,
    @SerializedName("DividendPerShare"           ) val DividendPerShare           : String? = null,
    @SerializedName("DividendYield"              ) val DividendYield              : String? = null,
    @SerializedName("EPS"                        ) val EPS                        : String? = null,
    @SerializedName("RevenuePerShareTTM"         ) val RevenuePerShareTTM         : String? = null,
    @SerializedName("ProfitMargin"               ) val ProfitMargin               : String? = null,
    @SerializedName("OperatingMarginTTM"         ) val OperatingMarginTTM         : String? = null,
    @SerializedName("ReturnOnAssetsTTM"          ) val ReturnOnAssetsTTM          : String? = null,
    @SerializedName("ReturnOnEquityTTM"          ) val ReturnOnEquityTTM          : String? = null,
    @SerializedName("RevenueTTM"                 ) val RevenueTTM                 : String? = null,
    @SerializedName("GrossProfitTTM"             ) val GrossProfitTTM             : String? = null,
    @SerializedName("DilutedEPSTTM"              ) val DilutedEPSTTM              : String? = null,
    @SerializedName("QuarterlyEarningsGrowthYOY" ) val QuarterlyEarningsGrowthYOY : String? = null,
    @SerializedName("QuarterlyRevenueGrowthYOY"  ) val QuarterlyRevenueGrowthYOY  : String? = null,
    @SerializedName("AnalystTargetPrice"         ) val AnalystTargetPrice         : String? = null,
    @SerializedName("AnalystRatingStrongBuy"     ) val AnalystRatingStrongBuy     : String? = null,
    @SerializedName("AnalystRatingBuy"           ) val AnalystRatingBuy           : String? = null,
    @SerializedName("AnalystRatingHold"          ) val AnalystRatingHold          : String? = null,
    @SerializedName("AnalystRatingSell"          ) val AnalystRatingSell          : String? = null,
    @SerializedName("AnalystRatingStrongSell"    ) val AnalystRatingStrongSell    : String? = null,
    @SerializedName("TrailingPE"                 ) val TrailingPE                 : String? = null,
    @SerializedName("ForwardPE"                  ) val ForwardPE                  : String? = null,
    @SerializedName("PriceToSalesRatioTTM"       ) val PriceToSalesRatioTTM       : String? = null,
    @SerializedName("PriceToBookRatio"           ) val PriceToBookRatio           : String? = null,
    @SerializedName("EVToRevenue"                ) val EVToRevenue                : String? = null,
    @SerializedName("EVToEBITDA"                 ) val EVToEBITDA                 : String? = null,
    @SerializedName("Beta"                       ) val Beta                       : String? = null,
    @SerializedName("52WeekHigh"                 ) val `52WeekHigh`               : String? = null,
    @SerializedName("52WeekLow"                  ) val `52WeekLow`                : String? = null,
    @SerializedName("50DayMovingAverage"         ) val `50DayMovingAverage`       : String? = null,
    @SerializedName("200DayMovingAverage"        ) val `200DayMovingAverage`      : String? = null,
    @SerializedName("SharesOutstanding"          ) val SharesOutstanding          : String? = null,
    @SerializedName("DividendDate"               ) val DividendDate               : String? = null,
    @SerializedName("ExDividendDate"             ) val ExDividendDate             : String? = null,
    @SerializedName(ERROR_MESSAGE                ) override val errorMessage             : String? = null,
    @SerializedName(INFORMATION                  ) override val information       : String? = null

): AlphaVantageResponse() {
    constructor(m: Mock): this(
        Symbol = "IBM",
        AssetType = "Common Stock",
        Name = "International Business Machines",
        Description = "International Business Machines Corporation (IBM) is an American multinational " +
                "technology company headquartered in Armonk, New York, with operations in over 170 countries. " +
                "The company began in 1911, founded in Endicott, New York, as the Computing-Tabulating-Recording " +
                "Company (CTR) and was renamed International Business Machines in 1924. IBM is incorporated in New York. " +
                "IBM produces and sells computer hardware, middleware and software, and provides hosting and " +
                "consulting services in areas ranging from mainframe computers to nanotechnology. " +
                "IBM is also a major research organization, holding the record for most annual U.S. " +
                "patents generated by a business (as of 2020) for 28 consecutive years. Inventions " +
                "by IBM include the automated teller machine (ATM), the floppy disk, the hard disk drive, " +
                "the magnetic stripe card, the relational database, the SQL programming language, the UPC barcode, " +
                "and dynamic random-access memory (DRAM). The IBM mainframe, exemplified by the System/360, " +
                "was the dominant computing platform during the 1960s and 1970s.",
        CIK = "51143",
        Exchange = "NYSE",
        Currency = "USD",
        Country = "USA",
        Sector = "TECHNOLOGY",
        Industry = "COMPUTER & OFFICE EQUIPMENT",
        Address = "1 NEW ORCHARD ROAD, ARMONK, NY, US",
        FiscalYearEnd = "December",
        LatestQuarter = "2024-03-31",
        MarketCapitalization = "161692500000",
        EBITDA = "14380000000",
        PERatio = "19.96",
        PEGRatio = "4.2",
        BookValue = "25.32",
        DividendPerShare = "6.64",
        DividendYield = "0.038",
        EPS = "8.82",
        RevenuePerShareTTM = "67.94",
        ProfitMargin = "0.132",
        OperatingMarginTTM = "0.102",
        ReturnOnAssetsTTM = "0.0458",
        ReturnOnEquityTTM = "0.362",
        RevenueTTM = "62068998000",
        GrossProfitTTM = "32688000000",
        DilutedEPSTTM = "8.82",
        QuarterlyEarningsGrowthYOY = "0.701",
        QuarterlyRevenueGrowthYOY = "0.015",
        AnalystTargetPrice = "181.05",
        AnalystRatingStrongBuy = "4",
        AnalystRatingBuy = "5",
        AnalystRatingHold = "9",
        AnalystRatingSell = "3",
        AnalystRatingStrongSell = "0",
        TrailingPE = "19.96",
        ForwardPE = "18.12",
        PriceToSalesRatioTTM = "2.701",
        PriceToBookRatio = "7.42",
        EVToRevenue = "3.453",
        EVToEBITDA = "14.54",
        Beta = "0.706",
        `52WeekHigh` = "197.22",
        `52WeekLow` = "127.16",
        `50DayMovingAverage` = "169.91",
        `200DayMovingAverage` = "168.02",
        SharesOutstanding = "918603000",
        DividendDate = "2024-06-10",
        ExDividendDate = "2024-05-09",
        errorMessage = null,
        information = null
    )
}