package com.sinisa.bragitask.features.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sinisa.bragitask.R

@Composable
fun CardListItem(
    modifier: Modifier = Modifier,
    cardListItemData: CardListItemData,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    imageHeight: Int = 240
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp),
                model = cardListItemData.image,
                contentDescription = "Movie poster for ${cardListItemData.title}",
                placeholder = painterResource(id = R.drawable.ic_image),
                error = painterResource(id = R.drawable.ic_image_broken)
            )
            Text(
                text = cardListItemData.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
            )
            Text(
                text = cardListItemData.formattedRating,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Budget: ${cardListItemData.formattedBudget}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Revenue: ${cardListItemData.formattedRevenue}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = cardListItemData.profitability,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun MovieListItemPreview() {
    CardListItem(
        modifier = Modifier,
        cardListItemData = CardListItemData(
            id = 1,
            title = "Movie Title",
            formattedRating = "★ 5.0",
            formattedBudget = "$1,000,000",
            formattedRevenue = "$2,000,000",
            profitability = "Profit: +100%",
            image = ""
        )
    )
}