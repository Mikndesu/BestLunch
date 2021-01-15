package com.mikn.bestlunch.model

data class SpecifiedStoreInfo(
    val hit_per_page: Int,
    val page_offset: Int,
    val rest: List<Rest>,
    val total_hit_count: Int
)

data class Attributes(
    val api_version: String
)