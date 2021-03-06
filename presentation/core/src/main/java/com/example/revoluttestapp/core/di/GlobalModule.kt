package com.example.revoluttestapp.core.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.revoluttestapp.core.LoggerImpl
import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.mappers.CurrencyRateMapperImpl
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.core.RxSchedulersImpl
import com.example.revoluttestapp.domain.utils.Logger
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class GlobalModule {
    @Singleton
    @Provides
    fun provideCurrencyRateMapper(): CurrencyRateMapper {
        return CurrencyRateMapperImpl()
    }

    @Singleton
    @Provides
    fun provideRetrofit(application: Application): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(application))
            .build()
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://hiring.revolut.codes/api/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRxSchedulers(): RxSchedulers {
        return RxSchedulersImpl()
    }

    @Singleton
    @Provides
    fun provideLogger(): Logger {
        return LoggerImpl()
    }
}