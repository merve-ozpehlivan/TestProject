package com.example.inventivtestcase.di

import com.example.inventivtestcase.api.CardsDaoInterface
import com.example.inventivtestcase.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://0565fb8b-cd64-4df3-ae54-8decf213404b.mock.pstmn.io/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitClient.getClient(BASE_URL)

    @Provides
    @Singleton
    fun provideCardsDao(retrofit: Retrofit): CardsDaoInterface {
        return retrofit.create(CardsDaoInterface::class.java)
    }
}
