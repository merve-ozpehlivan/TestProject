package com.example.inventivtestcase.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.inventivtestcase.R
import com.example.inventivtestcase.model.Card
import com.example.inventivtestcase.ui.theme.Beyaz
import com.example.inventivtestcase.ui.theme.Siyah
import com.example.inventivtestcase.utils.maskCardNumber


@Composable
fun CardListItem(
    card: Card,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = Beyaz,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.credit_card_svgrepo_com),
                    contentDescription = "Credit Card Icon",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = card.number.maskCardNumber(), color = Siyah)  // Burada çağırdık
                Text(text = "Bakiye: ${card.balance.value} ${card.balance.currency}")

            }
        }
    }
}
