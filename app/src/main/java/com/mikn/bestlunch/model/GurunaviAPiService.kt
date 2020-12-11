package com.mikn.bestlunch.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GurunaviAPiService {
    private val BASEURL = "https://api.gnavi.co.jp/RestSearchAPI/v3"

    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val service = Retrofit.Builder()
        .baseUrl(BASEURL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(GurunabiAPI::class.java)

    fun fetchStoreInfo() : Response<StoreInfo> {
        return service.fetchStoreInfo()
    }

}