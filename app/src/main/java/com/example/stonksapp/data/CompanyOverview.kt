package com.example.stonksapp.data

import com.example.stonksapp.utils.Mock
import com.google.gson.annotations.SerializedName

data class CompanyOverview (

    @SerializedName("Symbol"                     ) val symbol                     : String,
    @SerializedName("AssetType"                  ) val assetType                  : String,
    @SerializedName("Name"                       ) val name                       : String,
    @SerializedName("Description"                ) val description                : String,
    @SerializedName("CIK"                        ) val cik                        : String,
    @SerializedName("Exchange"                   ) val exchange                   : String,
    @SerializedName("Currency"                   ) val currency                   : String,
    @SerializedName("Country"                    ) val country                    : String,
    @SerializedName("Sector"                     ) val sector                     : String,
    @SerializedName("Industry"                   ) val industry                   : String,
    @SerializedName("Address"                    ) val address                    : String,
    @SerializedName("FiscalYearEnd"              ) val fiscalYearEnd              : String,
    @SerializedName("LatestQuarter"              ) val latestQuarter              : String,
    @SerializedName("MarketCapitalization"       ) val marketCapitalization       : String,
    @SerializedName("EBITDA"                     ) val eBITDA                     : String,
    @SerializedName("PERatio"                    ) val pERatio                    : String,
    @SerializedName("PEGRatio"                   ) val pEGRatio                   : String,
    @SerializedName("BookValue"                  ) val bookValue                  : String,
    @SerializedName("DividendPerShare"           ) val dividendPerShare           : String,
    @SerializedName("DividendYield"              ) val dividendYield              : String,
    @SerializedName("EPS"                        ) val ePS                        : String,
    @SerializedName("RevenuePerShareTTM"         ) val revenuePerShareTTM         : String,
    @SerializedName("ProfitMargin"               ) val profitMargin               : String,
    @SerializedName("OperatingMarginTTM"         ) val operatingMarginTTM         : String,
    @SerializedName("ReturnOnAssetsTTM"          ) val returnOnAssetsTTM          : String,
    @SerializedName("ReturnOnEquityTTM"          ) val returnOnEquityTTM          : String,
    @SerializedName("RevenueTTM"                 ) val revenueTTM                 : String,
    @SerializedName("GrossProfitTTM"             ) val grossProfitTTM             : String,
    @SerializedName("DilutedEPSTTM"              ) val dilutedEPSTTM              : String,
    @SerializedName("QuarterlyEarningsGrowthYOY" ) val quarterlyEarningsGrowthYOY : String,
    @SerializedName("QuarterlyRevenueGrowthYOY"  ) val quarterlyRevenueGrowthYOY  : String,
    @SerializedName("AnalystTargetPrice"         ) val analystTargetPrice         : String,
    @SerializedName("AnalystRatingStrongBuy"     ) val analystRatingStrongBuy     : String,
    @SerializedName("AnalystRatingBuy"           ) val analystRatingBuy           : String,
    @SerializedName("AnalystRatingHold"          ) val analystRatingHold          : String,
    @SerializedName("AnalystRatingSell"          ) val analystRatingSell          : String,
    @SerializedName("AnalystRatingStrongSell"    ) val analystRatingStrongSell    : String,
    @SerializedName("TrailingPE"                 ) val trailingPE                 : String,
    @SerializedName("ForwardPE"                  ) val forwardPE                  : String,
    @SerializedName("PriceToSalesRatioTTM"       ) val priceToSalesRatioTTM       : String,
    @SerializedName("PriceToBookRatio"           ) val priceToBookRatio           : String,
    @SerializedName("EVToRevenue"                ) val eVToRevenue                : String,
    @SerializedName("EVToEBITDA"                 ) val eVToEBITDA                 : String,
    @SerializedName("Beta"                       ) val beta                       : String,
    @SerializedName("52WeekHigh"                 ) val `52WeekHigh`               : String,
    @SerializedName("52WeekLow"                  ) val `52WeekLow`                : String,
    @SerializedName("50DayMovingAverage"         ) val `50DayMovingAverage`       : String,
    @SerializedName("200DayMovingAverage"        ) val `200DayMovingAverage`      : String,
    @SerializedName("SharesOutstanding"          ) val sharesOutstanding          : String,
    @SerializedName("DividendDate"               ) val dividendDate               : String,
    @SerializedName("ExDividendDate"             ) val exDividendDate             : String,
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