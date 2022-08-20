package com.miguelaguilar.superherocodechallengecoppel.injection

import com.miguelaguilar.superherocodechallengecoppel.data.network.AppAPI
import com.miguelaguilar.superherocodechallengecoppel.utlis.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun generateLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun generateHttpClientBuilder() = OkHttpClient.Builder()
        .addInterceptor(generateLoggingInterceptor())
        .addInterceptor { interceptorChain ->
            return@addInterceptor interceptorChain.proceed(
                interceptorChain
                    .request()
                    .newBuilder()
                    .build()
            )
        }


    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                generateHttpClientBuilder()
                    .build()
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideSuperHeroApiClient(retrofit: Retrofit) : AppAPI{
        return retrofit.create(AppAPI::class.java)
    }

}