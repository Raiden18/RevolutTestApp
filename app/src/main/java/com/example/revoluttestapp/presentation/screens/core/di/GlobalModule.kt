package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.mappers.CurrencyRateMapperImpl
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GlobalModule {
    @Singleton
    @Provides
    fun provideCurrencyRateMapper(): CurrencyRateMapper {
        return CurrencyRateMapperImpl()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://hiring.revolut.codes/api/android/") //TODO: MOVE TO build.gradle
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}