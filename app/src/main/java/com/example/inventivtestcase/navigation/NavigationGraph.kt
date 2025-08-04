package com.example.inventivtestcase.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventivtestcase.screen.CardDetailScreen
import com.example.inventivtestcase.screen.CardDetailViewModel
import com.example.inventivtestcase.screen.CardsScreen
import com.example.inventivtestcase.screen.CardsViewModel


object Routes {
    const val CARD_LIST = "cardList"
    const val CARD_DETAIL = "cardDetail"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val cardsViewModel: CardsViewModel = hiltViewModel()
    val cardDetailViewModel: CardDetailViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.CARD_LIST,
        modifier = modifier
    ) {
        composable(Routes.CARD_LIST) {
            CardsScreen(
                navController = navController,
                viewModel = cardsViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable("${Routes.CARD_DETAIL}/{cardNumber}") { backStackEntry ->
            val cardNumber = backStackEntry.arguments?.getString("cardNumber")
            CardDetailScreen(
                cardNumber = cardNumber,
                sharedViewModel = cardsViewModel,
                detailViewModel = cardDetailViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}