package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName

data class CompanyOverview (

    @SerializedName("Symbol"                     ) val symbol                     : String? = null,
    @SerializedName("AssetType"                  ) val assetType                  : String? = null,
    @SerializedName("Name"                       ) val name                       : String? = null,
    @SerializedName("Description"                ) val description                : String? = null,
    @SerializedName("CIK"                        ) val cik                        : String? = null,
    @SerializedName("Exchange"                   ) val exchange                   : String? = null,
    @SerializedName("Currency"                   ) val currency                   : String? = null,
    @SerializedName("Country"                    ) val country                    : String? = null,
    @SerializedName("Sector"                     ) val sector                     : String? = null,
    @SerializedName("Industry"                   ) val industry                   : String? = null,
    @SerializedName("Address"                    ) val address                    : String? = null,
    @SerializedName("FiscalYearEnd"              ) val fiscalYearEnd              : String? = null,
    @SerializedName("LatestQuarter"              ) val latestQuarter              : String? = null,
    @SerializedName("MarketCapitalization"       ) val marketCapitalization       : String? = null,
    @SerializedName("EBITDA"                     ) val eBITDA                     : String? = null,
    @SerializedName("PERatio"                    ) val pERatio                    : String? = null,
    @SerializedName("PEGRatio"                   ) val pEGRatio                   : String? = null,
    @SerializedName("BookValue"                  ) val bookValue                  : String? = null,
    @SerializedName("DividendPerShare"           ) val dividendPerShare           : String? = null,
    @SerializedName("DividendYield"              ) val dividendYield              : String? = null,
    @SerializedName("EPS"                        ) val ePS                        : String? = null,
    @SerializedName("RevenuePerShareTTM"         ) val revenuePerShareTTM         : String? = null,
    @SerializedName("ProfitMargin"               ) val profitMargin               : String? = null,
    @SerializedName("OperatingMarginTTM"         ) val operatingMarginTTM         : String? = null,
    @SerializedName("ReturnOnAssetsTTM"          ) val returnOnAssetsTTM          : String? = null,
    @SerializedName("ReturnOnEquityTTM"          ) val returnOnEquityTTM          : String? = null,
    @SerializedName("RevenueTTM"                 ) val revenueTTM                 : String? = null,
    @SerializedName("GrossProfitTTM"             ) val grossProfitTTM             : String? = null,
    @SerializedName("DilutedEPSTTM"              ) val dilutedEPSTTM              : String? = null,
    @SerializedName("QuarterlyEarningsGrowthYOY" ) val quarterlyEarningsGrowthYOY : String? = null,
    @SerializedName("QuarterlyRevenueGrowthYOY"  ) val quarterlyRevenueGrowthYOY  : String? = null,
    @SerializedName("AnalystTargetPrice"         ) val analystTargetPrice         : String? = null,
    @SerializedName("AnalystRatingStrongBuy"     ) val analystRatingStrongBuy     : String? = null,
    @SerializedName("AnalystRatingBuy"           ) val analystRatingBuy           : String? = null,
    @SerializedName("AnalystRatingHold"          ) val analystRatingHold          : String? = null,
    @SerializedName("AnalystRatingSell"          ) val analystRatingSell          : String? = null,
    @SerializedName("AnalystRatingStrongSell"    ) val analystRatingStrongSell    : String? = null,
    @SerializedName("TrailingPE"                 ) val trailingPE                 : String? = null,
    @SerializedName("ForwardPE"                  ) val forwardPE                  : String? = null,
    @SerializedName("PriceToSalesRatioTTM"       ) val priceToSalesRatioTTM       : String? = null,
    @SerializedName("PriceToBookRatio"           ) val priceToBookRatio           : String? = null,
    @SerializedName("EVToRevenue"                ) val eVToRevenue                : String? = null,
    @SerializedName("EVToEBITDA"                 ) val eVToEBITDA                 : String? = null,
    @SerializedName("Beta"                       ) val beta                       : String? = null,
    @SerializedName("52WeekHigh"                 ) val `52WeekHigh`               : String? = null,
    @SerializedName("52WeekLow"                  ) val `52WeekLow`                : String? = null,
    @SerializedName("50DayMovingAverage"         ) val `50DayMovingAverage`       : String? = null,
    @SerializedName("200DayMovingAverage"        ) val `200DayMovingAverage`      : String? = null,
    @SerializedName("SharesOutstanding"          ) val sharesOutstanding          : String? = null,
    @SerializedName("DividendDate"               ) val dividendDate               : String? = null,
    @SerializedName("ExDividendDate"             ) val exDividendDate             : String? = null,
    @SerializedName(ERROR_MESSAGE                ) override val errorMessage      : String? = null,
    @SerializedName(INFORMATION                  ) override val information       : String? = null

): AlphaVantageResponse() {
    constructor(m: Mock): this(
        symbol = "IBM",
        assetType = "Common Stock",
        name = "International Business Machines",
        description = "International Business Machines Corporation (IBM) is an American multinational " +
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
        cik = "51143",
        exchange = "NYSE",
        currency = "USD",
        country = "USA",
        sector = "TECHNOLOGY",
        industry = "COMPUTER & OFFICE EQUIPMENT",
        address = "1 NEW ORCHARD ROAD, ARMONK, NY, US",
        fiscalYearEnd = "December",
        latestQuarter = "2024-03-31",
        marketCapitalization = "161692500000",
        eBITDA = "14380000000",
        pERatio = "19.96",
        pEGRatio = "4.2",
        bookValue = "25.32",
        dividendPerShare = "6.64",
        dividendYield = "0.038",
        ePS = "8.82",
        revenuePerShareTTM = "67.94",
        profitMargin = "0.132",
        operatingMarginTTM = "0.102",
        returnOnAssetsTTM = "0.0458",
        returnOnEquityTTM = "0.362",
        revenueTTM = "62068998000",
        grossProfitTTM = "32688000000",
        dilutedEPSTTM = "8.82",
        quarterlyEarningsGrowthYOY = "0.701",
        quarterlyRevenueGrowthYOY = "0.015",
        analystTargetPrice = "181.05",
        analystRatingStrongBuy = "4",
        analystRatingBuy = "5",
        analystRatingHold = "9",
        analystRatingSell = "3",
        analystRatingStrongSell = "0",
        trailingPE = "19.96",
        forwardPE = "18.12",
        priceToSalesRatioTTM = "2.701",
        priceToBookRatio = "7.42",
        eVToRevenue = "3.453",
        eVToEBITDA = "14.54",
        beta = "0.706",
        `52WeekHigh` = "197.22",
        `52WeekLow` = "127.16",
        `50DayMovingAverage` = "169.91",
        `200DayMovingAverage` = "168.02",
        sharesOutstanding = "918603000",
        dividendDate = "2024-06-10",
        exDividendDate = "2024-05-09",
        errorMessage = null,
        information = null
    )
}