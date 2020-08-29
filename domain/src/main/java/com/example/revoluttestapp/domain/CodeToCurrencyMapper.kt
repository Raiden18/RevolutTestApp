package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.currencies.*
import java.lang.IllegalStateException


class CodeToCurrencyMapper {
    private companion object{
        const val EMPTY_ZERO_VALUE = 0.toDouble()
    }
    fun map(code: String): Currency {
        return when (code) {
            Euro.CODE -> Euro(
                0.0
            )
            AustralianDollar.CODE -> AustralianDollar(EMPTY_ZERO_VALUE)
            BrazilianReal.CODE -> BrazilianReal(EMPTY_ZERO_VALUE)
            BulgarianLev.CODE -> BulgarianLev(EMPTY_ZERO_VALUE)
            CanadianDollar.CODE -> CanadianDollar(EMPTY_ZERO_VALUE)
            CroatianKuna.CODE -> CroatianKuna(EMPTY_ZERO_VALUE)
            CzechKoruna.CODE -> CroatianKuna(EMPTY_ZERO_VALUE)
            DanishKrone.CODE -> DanishKrone(EMPTY_ZERO_VALUE)
            HongKongDollar.CODE -> HongKongDollar(EMPTY_ZERO_VALUE)
            HungarianForint.CODE -> HungarianForint(EMPTY_ZERO_VALUE)
            IcelandicKrona.CODE -> IcelandicKrona(EMPTY_ZERO_VALUE)
            IndianRupee.CODE -> IndianRupee(EMPTY_ZERO_VALUE)
            IndonesianRupiah.CODE -> IndonesianRupiah(EMPTY_ZERO_VALUE)
            IsraeliNewShekel.CODE -> IsraeliNewShekel(EMPTY_ZERO_VALUE)
            JapaneseYen.CODE -> JapaneseYen(EMPTY_ZERO_VALUE)
            MalaysianRinggit.CODE -> MalaysianRinggit(EMPTY_ZERO_VALUE)
            MexicanPeso.CODE -> MexicanPeso(EMPTY_ZERO_VALUE)
            NewZealandDollar.CODE -> NewZealandDollar(EMPTY_ZERO_VALUE)
            NorwegianKrone.CODE -> NorwegianKrone(EMPTY_ZERO_VALUE)
            PhilippinePeso.CODE -> PhilippinePeso(EMPTY_ZERO_VALUE)
            PolishZloty.CODE -> PolishZloty(EMPTY_ZERO_VALUE)
            PoundSterling.CODE -> PoundSterling(EMPTY_ZERO_VALUE)
            Renminbi.CODE -> Renminbi(EMPTY_ZERO_VALUE)
            RomanianLeu.CODE -> RomanianLeu(EMPTY_ZERO_VALUE)
            RussianRouble.CODE -> RussianRouble(EMPTY_ZERO_VALUE)
            SingaporeDollar.CODE -> SingaporeDollar(EMPTY_ZERO_VALUE)
            SouthAfricanRand.CODE -> SouthAfricanRand(EMPTY_ZERO_VALUE)
            SouthKoreanWon.CODE -> SouthKoreanWon(EMPTY_ZERO_VALUE)
            SwedishKrona.CODE -> SwedishKrona(EMPTY_ZERO_VALUE)
            SwissFrank.CODE -> SwissFrank(EMPTY_ZERO_VALUE)
            ThaiBaht.CODE -> ThaiBaht(EMPTY_ZERO_VALUE)
            UnitedStatesDollar.CODE -> UnitedStatesDollar(EMPTY_ZERO_VALUE)
            else -> throw IllegalStateException("Unknown currency")
        }
    }
}