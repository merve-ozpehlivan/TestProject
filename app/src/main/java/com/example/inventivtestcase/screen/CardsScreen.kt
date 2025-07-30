package com.example.inventivtestcase.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.inventivtestcase.navigation.Routes
import com.example.inventivtestcase.ui.theme.AcikYesil
import com.example.inventivtestcase.ui.theme.Beyaz
import com.example.inventivtestcase.ui.theme.Siyah
import com.example.inventivtestcase.ui.theme.Yesil

@Composable
fun CardsScreen(
    navController: NavHostController,
    viewModel: CardsViewModel
) {
    val cards by viewModel.cards.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCards()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
                title = { Text("Card List") },
                backgroundColor = Yesil,
                contentColor = Beyaz
            )
        },
        backgroundColor = AcikYesil
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
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
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopCenter)
                    )
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(cards) { card ->
                            CardListItem(
                                card = card,
                                onClick = {
                                    navController.navigate(
                                        "${Routes.CardDetail}/${
                                            card.number.replace(
                                                " ",
                                                ""
                                            )
                                        }"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
