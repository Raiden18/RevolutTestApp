package com.example.revoluttestapp.domain.models.currency

sealed class Currencies {
    class AustralianDollar: Currencies(){
        companion object{
            const val SHORT_NAME ="AUD"
        }
    }
    class BulgarianLev: Currencies(){
        companion object{
            const val SHORT_NAME ="BGN"
        }
    }
     class BrazilianReal: Currencies(){
        companion object{
            const val SHORT_NAME ="BRL"
        }
    }
     class CanadianDollar: Currencies(){
        companion object{
            const val SHORT_NAME ="CAD"
        }
    }
     class SwissFrank: Currencies(){
        companion object{
            const val SHORT_NAME ="CHF"
        }
    }
     class Renminbi: Currencies(){
        companion object{
            const val SHORT_NAME ="CNY"
        }
    }
     class CzechKoruna: Currencies(){
        companion object{
            const val SHORT_NAME ="CZK"
        }
    }
     class DanishKrone: Currencies(){
        companion object{
            const val SHORT_NAME ="DKK"
        }
    }
     class PoundSterling: Currencies(){
        companion object{
            const val SHORT_NAME ="GBP"
        }
    }
     class HongKongDollar: Currencies(){
        companion object{
            const val SHORT_NAME ="HKD"
        }
    }
     class CroatianKuna: Currencies(){
        companion object{
            const val SHORT_NAME ="HRK"
        }
    }
     class HungarianForint: Currencies(){
        companion object{
            const val SHORT_NAME ="HUF"
        }
    }
     class IndonesianRupiah: Currencies(){
        companion object{
            const val SHORT_NAME ="IDR"
        }
    }
     class IsraeliNewShekel: Currencies(){
        companion object{
            const val SHORT_NAME ="ILS"
        }
    }
     class IndianRupee : Currencies(){
        companion object{
            const val SHORT_NAME ="INR"
        }
    }
     class IcelandicKrona: Currencies(){
        companion object{
            const val SHORT_NAME ="ISK"
        }
    }
     class JapaneseYen: Currencies(){
        companion object{
            const val SHORT_NAME ="JPY"
        }
    }
     class SouthKoreanWon: Currencies(){
        companion object{
            const val SHORT_NAME ="KRW"
        }
    }
     class MexicanPeso: Currencies(){
        companion object{
            const val SHORT_NAME ="MXN"
        }
    }
     class MalaysianRinggit: Currencies(){
        companion object{
            const val SHORT_NAME ="MYR"
        }
    }
     class NorwegianKrone: Currencies(){
        companion object{
            const val SHORT_NAME ="NOK"
        }
    }
     class NewZealandDollar: Currencies(){
        companion object{
            const val SHORT_NAME ="NZD"
        }
    }
     class PhilippinePeso: Currencies(){
        companion object{
            const val SHORT_NAME ="PHP"
        }
    }
     class PolishZloty: Currencies(){
        companion object{
            const val SHORT_NAME ="PLN"
        }
    }
     class RomanianLeu: Currencies(){
        companion object{
            const val SHORT_NAME ="RON"
        }
    }
     class RussianRouble: Currencies(){
        companion object{
            const val SHORT_NAME ="RUB"
        }
    }
     class SwedishKrona: Currencies(){
        companion object{
            const val SHORT_NAME ="SEK"
        }
    }
     class SingaporeDollar: Currencies(){
        companion object{
            const val SHORT_NAME ="SGD"
        }
    }
     class ThaiBaht: Currencies(){
        companion object{
            const val SHORT_NAME ="THB"
        }
    }
     class UnitedStatesDollar: Currencies(){
        companion object{
            const val SHORT_NAME ="USD"
        }
    }
     class SouthAfricanRand: Currencies(){
        companion object{
            const val SHORT_NAME ="ZAR"
        }
    }
}