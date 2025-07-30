package com.example.inventivtestcase.screen

import androidx.lifecycle.ViewModel
import com.example.inventivtestcase.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardDetailViewModel : ViewModel() {

    private val _card = MutableStateFlow<Card?>(null)
    val card: StateFlow<Card?> = _card

    fun setCard(card: Card) {
        _card.value = card
    }
}
