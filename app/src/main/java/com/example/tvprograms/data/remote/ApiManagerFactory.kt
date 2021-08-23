package com.example.tvprograms.data.remote

import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManagerFactory{


    fun makeRetrofitService(): ProgramsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(makeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProgramsApi::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().apply {
            addInterceptor(makeLoggingInterceptor())
        }
        return httpBuilder.build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        return logging
    }

}