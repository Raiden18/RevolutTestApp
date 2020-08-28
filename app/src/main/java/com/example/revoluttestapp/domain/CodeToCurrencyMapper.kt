package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.currencies.*
import java.lang.IllegalStateException

//TODO: add constant for 0.0
class CodeToCurrencyMapper {
    fun map(code: String): Currency {
        return when (code) {
            Euro.CODE -> Euro(0.0)
            AustralianDollar.CODE -> AustralianDollar(0.0)
            BrazilianReal.CODE -> BrazilianReal(0.0)
            BulgarianLev.CODE -> BulgarianLev(0.0)
            CanadianDollar.CODE -> CanadianDollar(0.0)
            CroatianKuna.CODE -> CroatianKuna(0.0)
            CzechKoruna.CODE -> CroatianKuna(0.0)
            DanishKrone.CODE -> DanishKrone(0.0)
            HongKongDollar.CODE -> HongKongDollar(0.0)
            HungarianForint.CODE -> HungarianForint(0.0)
            IcelandicKrona.CODE -> IcelandicKrona(0.0)
            IndianRupee.CODE -> IndianRupee(0.0)
            IndonesianRupiah.CODE -> IndonesianRupiah(0.0)
            IsraeliNewShekel.CODE -> IsraeliNewShekel(0.0)
            JapaneseYen.CODE -> JapaneseYen(0.0)
            MalaysianRinggit.CODE -> MalaysianRinggit(0.0)
            MexicanPeso.CODE -> MexicanPeso(0.0)
            NewZealandDollar.CODE -> NewZealandDollar(0.0)
            NorwegianKrone.CODE -> NorwegianKrone(0.0)
            PhilippinePeso.CODE -> PhilippinePeso(0.0)
            PolishZloty.CODE -> PolishZloty(0.0)
            PoundSterling.CODE -> PoundSterling(0.0)
            Renminbi.CODE -> Renminbi(0.0)
            RomanianLeu.CODE -> RomanianLeu(0.0)
            RussianRouble.CODE -> RussianRouble(0.0)
            SingaporeDollar.CODE -> SingaporeDollar(0.0)
            SouthAfricanRand.CODE -> SouthAfricanRand(0.0)
            SouthKoreanWon.CODE -> SouthKoreanWon(0.0)
            SwedishKrona.CODE -> SwedishKrona(0.0)
            SwissFrank.CODE -> SwissFrank(0.0)
            ThaiBaht.CODE -> ThaiBaht(0.0)
            UnitedStatesDollar.CODE -> UnitedStatesDollar(0.0)
            else -> throw IllegalStateException("Unknown currency")
        }
    }
}