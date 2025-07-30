package com.example.inventivtestcase.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventivtestcase.api.ApiUtils
import com.example.inventivtestcase.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val api = ApiUtils.getCardsDaoInterface()

    fun fetchCards() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val cardList = api.getCards()
                Log.d("CardsViewModel", "Gelen kart sayısı: ${cardList.size}")
                _cards.value = cardList
            } catch (e: Exception) {
                Log.e("CardsViewModel", "API call failed: ${e.localizedMessage}", e)
                _cards.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
