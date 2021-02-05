package com.mikn.bestlunch.model

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class HotPepperAPIService {
    private val BASEURL = "https://webservice.recruit.co.jp/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(HotPepperAPI::class.java)

    fun getRestaurant(lat:Double, lng:Double): StoreInfo? {
        val queryParams: Map<String, String> = mapOf(
            "key" to "373bf1b0ab2c612f",
            "lat" to lat.toString(),
            "lng" to lng.toString(),
            "count" to "50",
            "format" to "json"
        )
        try {
            val response = api.getRestaurant(queryParams).execute()
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.d("tag", "GET ERROR")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}