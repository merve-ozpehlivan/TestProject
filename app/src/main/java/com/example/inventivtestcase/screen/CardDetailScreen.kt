package com.example.inventivtestcase.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventivtestcase.R
import com.example.inventivtestcase.ui.theme.KoyuYesil
import com.example.inventivtestcase.ui.theme.Siyah
import com.example.inventivtestcase.utils.formatAsTLFromMinorUnit
import com.example.inventivtestcase.utils.maskCardNumber

@Composable
fun CardDetailScreen(
    cardNumber: String?,
    sharedViewModel: CardsViewModel,
    detailViewModel: CardDetailViewModel,
    modifier: Modifier = Modifier
) {
    val cards by sharedViewModel.cards.collectAsState()
    val matchedCard = remember(cardNumber, cards) {
        cards.find { it.number.replace(" ", "") == cardNumber }
    }

    LaunchedEffect(matchedCard) {
        matchedCard?.let { detailViewModel.setCard(it) }
    }

    val card by detailViewModel.card.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        card?.let { card ->
            Card(
                backgroundColor = KoyuYesil,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sim_card_chip_svgrepo_com),
                            contentDescription = "Chip",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )

                        Text(
                            text = card.number.maskCardNumber(),
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                "CVV: ${card.cvv}",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "Bakiye: ${card.balance.value.formatAsTLFromMinorUnit()}",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "Bekleyen: ${card.pendingBalance.value.formatAsTLFromMinorUnit()}",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.mastercard_full_svgrepo_com),
                        contentDescription = "Kart Tipi",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(50.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        } ?: Text(
            text = "Kart bulunamadı.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Siyah,
            textAlign = TextAlign.Center
        )
    }
}