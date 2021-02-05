package com.mikn.bestlunch.model

data class StoreInfo(
    val results: Results
)

data class Results(
    val api_version: String,
    val results_available: Int,
    val results_returned: String,
    val results_start: Int,
    val shop: List<Shop>
)

data class Shop(
    val access: String,
    val address: String,
    val band: String,
    val barrier_free: String,
    val budget: Budget? = null,
    val budget_memo: String,
    val capacity: Any?,
    val card: String,
    val `catch`: String,
    val charter: String,
    val child: String,
    val close: String,
    val coupon_urls: CouponUrls? = null,
    val course: String,
    val english: String,
    val free_drink: String,
    val free_food: String,
    val genre: Genre,
    val horigotatsu: String,
    val id: String,
    val karaoke: String,
    val ktai_coupon: Int,
    val large_area: LargeArea,
    val large_service_area: LargeServiceArea,
    val lat: Double,
    val lng: Double,
    val logo_image: String,
    val lunch: String,
    val middle_area: MiddleArea,
    val midnight: String,
    val mobile_access: String,
    val name: String,
    val name_kana: String,
    val non_smoking: String,
    val `open`: String,
    val other_memo: String,
    val parking: String,
    val party_capacity: String,
    val pet: String,
    val photo: Photo,
    val private_room: String,
    val service_area: ServiceArea,
    val shop_detail_memo: String,
    val show: String,
    val small_area: SmallArea,
    val station_name: String,
    val sub_genre: SubGenre?,
    val tatami: String,
    val tv: String,
    val urls: Urls,
    val wedding: String,
    val wifi: String,
    @Transient var distance: Double = 0.0
)

data class Budget(
    val average: String,
    val code: String,
    val name: String
)

data class CouponUrls(
    val pc: String,
    val sp: String
)

data class LargeArea(
    val code: String,
    val name: String
)

data class LargeServiceArea(
    val code: String,
    val name: String
)

data class MiddleArea(
    val code: String,
    val name: String
)

data class ServiceArea(
    val code: String,
    val name: String
)

data class SmallArea(
    val code: String,
    val name: String
)

data class SubGenre(
    val code: String,
    val name: String
)

data class Genre(
    val `catch`: String,
    val code: String,
    val name: String
)

data class Mobile(
    val l: String,
    val s: String
)

data class Pc(
    val l: String,
    val m: String,
    val s: String
)

data class Photo(
    val mobile: Mobile,
    val pc: Pc
)

data class Urls(
    val pc: String
)