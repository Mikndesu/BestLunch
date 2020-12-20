package com.mikn.bestlunch.model

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class GurunaviAPiService {
    private val BASEURL = "https://api.gnavi.co.jp/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(GurunabiAPI::class.java)

    fun getRestaurant(): StoreInfo? {
        val queryParams: Map<String, String> = mapOf(
            "keyid" to "da2541d0286020a43695e9d28baa0b28",
            "latitude" to "34.6974406",
            "longitude" to "135.4923925",
            "range" to "5",
            "hit_per_page" to "50",
            "category_l" to "RSFST08000"
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