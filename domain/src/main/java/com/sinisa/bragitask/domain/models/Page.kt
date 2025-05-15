package com.sinisa.bragitask.domain.models

data class Page<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)
