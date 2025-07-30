package com.example.inventivtestcase.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inventivtestcase.R
import com.example.inventivtestcase.ui.theme.AcikYesil
import com.example.inventivtestcase.ui.theme.Beyaz
import com.example.inventivtestcase.ui.theme.Siyah
import com.example.inventivtestcase.ui.theme.Yesil
import com.example.inventivtestcase.utils.maskCardNumber

@Composable
fun CardDetailScreen(
    cardNumber: String?,
    sharedViewModel: CardsViewModel,
    detailViewModel: CardDetailViewModel,
    navController: NavController
) {
    val cards by sharedViewModel.cards.collectAsState()
    val matchedCard = remember(cardNumber, cards) {
        cards.find { it.number.replace(" ", "") == cardNumber }
    }

    LaunchedEffect(matchedCard) {
        matchedCard?.let { detailViewModel.setCard(it) }
    }

    val card by detailViewModel.card.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
                title = { Text("Card Details") },
                backgroundColor = Yesil,
                contentColor = Beyaz,
                actions = {
                    Surface(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(32.dp),
                        shape = CircleShape,
                        color = Beyaz,
                        elevation = 6.dp
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_backup_2_svgrepo_com),
                                contentDescription = "Geri Dön",
                                modifier = Modifier.size(20.dp),
                                tint = Siyah
                            )
                        }
                    }
                }
            )
        },
        backgroundColor = AcikYesil
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (card == null) {
                Text(
                    text = "Kart bulunamadı.",
                    modifier = Modifier.padding(16.dp),
                    color = Siyah
                )
            } else {
                Card(
                    backgroundColor = Beyaz,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 75.dp)
                        .padding(16.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Kart No: ${card!!.number.maskCardNumber()}", color = Siyah)
                        Text(text = "CVV: ${card!!.cvv}", color = Siyah)
                        Text(
                            text = "Bakiye: ${card!!.balance.value} ${card!!.balance.currency}",
                            color = Siyah
                        )
                        Text(
                            text = "Bekleyen: ${card!!.pendingBalance.value} ${card!!.pendingBalance.currency}",
                            color = Siyah
                        )
                    }
                }
            }
        }
    }
}
