package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.data.models.response.CurrencyRatesResponse
import com.example.revoluttestapp.data.models.response.CurrencyResponse
import com.example.revoluttestapp.domain.models.currency.Currencies
import com.example.revoluttestapp.domain.models.currency.CurrencyRate
import com.example.revoluttestapp.domain.models.currency.CurrencyRates

class CurrencyRateMapperImpl : CurrencyRateMapper {
    override fun map(currencyResponse: CurrencyResponse): CurrencyRates {
        val currencyRatesResponse = currencyResponse.rates
        return if (currencyRatesResponse == null) {
            CurrencyRates.creteEmpty()
        } else {
            mapCurrencyRates(currencyRatesResponse)
        }
    }

    private fun mapCurrencyRates(currencyRatesResponse: CurrencyRatesResponse): CurrencyRates {
        val currencies = arrayListOf<CurrencyRate>()
        if (currencyRatesResponse.aUD != null) {
            val australianDollarRate =
                CurrencyRate(Currencies.AustralianDollar.SHORT_NAME, currencyRatesResponse.aUD)
            currencies.add(australianDollarRate)
        }
        if (currencyRatesResponse.bGN != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.BulgarianLev.SHORT_NAME, currencyRatesResponse.bGN)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.bRL != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.BrazilianReal.SHORT_NAME, currencyRatesResponse.bRL)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.cAD != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.CanadianDollar.SHORT_NAME, currencyRatesResponse.cAD)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.cHF != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.SwissFrank.SHORT_NAME, currencyRatesResponse.cHF)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.iLS != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.IsraeliNewShekel.SHORT_NAME, currencyRatesResponse.iLS)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.iNR != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.IndianRupee.SHORT_NAME, currencyRatesResponse.iNR)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.iSK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.IcelandicKrona.SHORT_NAME, currencyRatesResponse.iSK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.jPY != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.JapaneseYen.SHORT_NAME, currencyRatesResponse.jPY)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.mXN != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.MexicanPeso.SHORT_NAME, currencyRatesResponse.mXN)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.mYR != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.MalaysianRinggit.SHORT_NAME, currencyRatesResponse.mYR)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.nOK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.NorwegianKrone.SHORT_NAME, currencyRatesResponse.nOK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.nZD != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.NewZealandDollar.SHORT_NAME, currencyRatesResponse.nZD)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.pHP != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.PhilippinePeso.SHORT_NAME, currencyRatesResponse.pHP)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.pLN != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.PolishZloty.SHORT_NAME, currencyRatesResponse.pLN)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.rON != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.RomanianLeu.SHORT_NAME, currencyRatesResponse.rON)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.rUB != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.RussianRouble.SHORT_NAME, currencyRatesResponse.rUB)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.sEK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.SwedishKrona.SHORT_NAME, currencyRatesResponse.sEK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.sGD != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.SingaporeDollar.SHORT_NAME, currencyRatesResponse.sGD)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.tHB != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.ThaiBaht.SHORT_NAME, currencyRatesResponse.tHB)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.uSD != null) {
            val bulgarianLevRate =
                CurrencyRate(
                    Currencies.UnitedStatesDollar.SHORT_NAME,
                    currencyRatesResponse.uSD
                )
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.zAR != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.SouthAfricanRand.SHORT_NAME, currencyRatesResponse.zAR)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.dKK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.DanishKrone.SHORT_NAME, currencyRatesResponse.dKK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.gBP != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.PoundSterling.SHORT_NAME, currencyRatesResponse.gBP)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.hKD != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.HongKongDollar.SHORT_NAME, currencyRatesResponse.hKD)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.hUF != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.HungarianForint.SHORT_NAME, currencyRatesResponse.hUF)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.iDR != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.IndonesianRupiah.SHORT_NAME, currencyRatesResponse.iDR)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.cZK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.CzechKoruna.SHORT_NAME, currencyRatesResponse.cZK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.dKK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.BulgarianLev.SHORT_NAME, currencyRatesResponse.dKK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.cNY != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.Renminbi.SHORT_NAME, currencyRatesResponse.cNY)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.hRK != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.CroatianKuna.SHORT_NAME, currencyRatesResponse.hRK)
            currencies.add(bulgarianLevRate)
        }
        if (currencyRatesResponse.kRW != null) {
            val bulgarianLevRate =
                CurrencyRate(Currencies.SouthKoreanWon.SHORT_NAME, currencyRatesResponse.kRW)
            currencies.add(bulgarianLevRate)
        }
        return CurrencyRates(currencies)
    }
}