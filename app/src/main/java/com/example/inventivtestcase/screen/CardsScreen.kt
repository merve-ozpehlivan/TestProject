package com.example.inventivtestcase.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.inventivtestcase.navigation.Routes
import com.example.inventivtestcase.ui.theme.Siyah
import com.example.inventivtestcase.ui.theme.Yesil


@Composable
fun CardsScreen(
    navController: NavHostController,
    viewModel: CardsViewModel,
    modifier: Modifier = Modifier
) {
    val cards by viewModel.cards.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(true) {
        viewModel.fetchCards()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    color = Yesil,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            cards.isEmpty() -> {
                Text(
                    text = "Hiç kart bulunamadı.",
                    color = Siyah,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cards) { card ->
                        CardListItem(
                            card = card,
                            onClick = {
                                navController.navigate(
                                    "${Routes.CARD_DETAIL}/${card.number.replace(" ", "")}"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
