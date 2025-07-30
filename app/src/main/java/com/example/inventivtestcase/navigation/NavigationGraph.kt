package com.example.inventivtestcase.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventivtestcase.screen.CardDetailScreen
import com.example.inventivtestcase.screen.CardDetailViewModel
import com.example.inventivtestcase.screen.CardsScreen
import com.example.inventivtestcase.screen.CardsViewModel

object Routes {
    const val CardList = "cardList"
    const val CardDetail = "cardDetail"
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val cardsViewModel: CardsViewModel = viewModel()
    val cardDetailViewModel: CardDetailViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.CardList) {
        composable(Routes.CardList) {
            CardsScreen(
                navController,
                cardsViewModel
            )
        }
        composable("${Routes.CardDetail}/{cardNumber}") { backStackEntry ->
            val cardNumber = backStackEntry.arguments?.getString("cardNumber")
            CardDetailScreen(
                cardNumber,
                cardsViewModel,
                cardDetailViewModel,
                navController
            )
        }

    }
}
