package com.github.aicoleman.digimon_flow_sample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Digimon(
    val name: String,
    val img: String,
    val level: String
)