package com.sinisa.bragitask.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val rating: Double
)