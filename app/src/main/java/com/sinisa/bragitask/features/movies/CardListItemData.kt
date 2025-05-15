package com.sinisa.bragitask.features.movies

data class CardListItemData(
    val id: Int,
    val title: String,
    val formattedRating: String,
    val formattedBudget: String,
    val formattedRevenue: String,
    val profitability: String,
    val image: String
)