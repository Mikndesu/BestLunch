package com.mikn.bestlunch.model

data class StoreInfo(
    val hit_per_page: Int,
    val page_offset: Int,
    val rest: List<Rest>,
    val total_hit_count: Int
)

data class Rest(
    val access: Access,
    val address: String,
    val budget: Any,
    val category: String,
    val code: Code,
    val coupon_url: CouponUrl,
    val credit_card: String,
    val e_money: String,
    val fax: String,
    val flags: Flags,
    val holiday: String,
    val id: String,
    val image_url: ImageUrl,
    val latitude: String,
    val longitude: String,
    val lunch: Any,
    val name: String,
    val name_kana: String,
    val opentime: String,
    val parking_lots: String,
    val party: Any,
    val pr: Pr,
    val tel: String,
    val tel_sub: String,
    val update_date: String,
    val url: String,
    val url_mobile: String
)

data class AttributesX(
    val order: Int
)

data class Access(
    val line: String,
    val note: String,
    val station: String,
    val station_exit: String,
    val walk: String
)

data class Code(
    val areacode: String,
    val areacode_s: String,
    val areaname: String,
    val areaname_s: String,
    val category_code_l: List<String>,
    val category_code_s: List<String>,
    val category_name_l: List<String>,
    val category_name_s: List<String>,
    val prefcode: String,
    val prefname: String
)

data class CouponUrl(
    val mobile: String,
    val pc: String
)

data class Flags(
    val mobile_coupon: Int,
    val mobile_site: Int,
    val pc_coupon: Int
)

data class ImageUrl(
    val qrcode: String,
    val shop_image1: String,
    val shop_image2: String
)

data class Pr(
    val pr_long: String,
    val pr_short: String
)