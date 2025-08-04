package com.example.inventivtestcase.screen

import androidx.lifecycle.ViewModel
import com.example.inventivtestcase.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CardDetailViewModel @Inject constructor() : ViewModel() {

    private val _card = MutableStateFlow<Card?>(null)
    val card: StateFlow<Card?> = _card

    fun setCard(card: Card) {
        _card.value = card
    }
}
