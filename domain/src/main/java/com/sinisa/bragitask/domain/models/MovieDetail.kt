package com.sinisa.bragitask.domain.models

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val rating: Double,
    val budget: Long,
    val revenue: Long
)
