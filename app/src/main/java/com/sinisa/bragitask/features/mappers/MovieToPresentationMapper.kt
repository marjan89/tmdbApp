package com.sinisa.bragitask.features.mappers

import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.features.movies.CardListItemData
import java.text.NumberFormat
import kotlin.math.roundToInt

fun Movie.mapToPresentation(): CardListItemData {
    val currencyFormat = NumberFormat.getCurrencyInstance()
    val isProfitable = revenue > budget
    val roi = if (budget > 0) ((revenue - budget).toDouble() / budget.toDouble() * 100).roundToInt() else 0

    return CardListItemData(
        id = id,
        title = title,
        formattedRating = "★ ${rating.toString().take(3)}",
        formattedBudget = currencyFormat.format(budget),
        formattedRevenue = currencyFormat.format(revenue),
        profitability = if (isProfitable) "Profit: +${roi}%" else "Loss: ${roi}%",
        image = image
    )
}
