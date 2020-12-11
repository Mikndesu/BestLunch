package com.mikn.bestlunch.model

import retrofit2.Response
import retrofit2.http.GET

interface GurunabiAPI {
    @GET("?keyid=da2541d0286020a43695e9d28baa0b28&latitude=34.6974406&longitude=135.4923925&range=5&hit_per_page=50&category_l=RSFST08000")
    fun fetchStoreInfo() : Response<StoreInfo>
}
