package com.mikn.bestlunch.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GurunabiAPI {
    @GET("RestSearchAPI/v3")
    fun getRestaurant(@QueryMap params: Map<String, String>) : Call<StoreInfo>
    fun getSpecifiedRestaurant(@QueryMap id: String) : Call<SpecifiedStoreInfo>
}
