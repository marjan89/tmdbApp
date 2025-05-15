package com.sinisa.bragitask.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val image: String,
    val rating: Double,
    val budget: Long,
    val revenue: Long
)