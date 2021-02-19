package com.mikn.bestlunch.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HotPepperAPI {
    @GET("hotpepper/gourmet/v1/")
    fun getRestaurant(@QueryMap params: Map<String, String>) : Call<StoreInfo>
}
