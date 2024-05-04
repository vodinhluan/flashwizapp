package com.example.flashwiz_fe.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.remote.CardApiService
import kotlinx.coroutines.launch

class StatisticViewModel(private val cardApiService: CardApiService, private val flashcardId: Int) : ViewModel() {
    var totalCard: Int = 0
        private set
    var newCard: Int = 0
        private set
    var failCard: Int = 0
        private set
    var hardCard: Int = 0
        private set
    var goodCard: Int = 0
        private set
    var easyCard: Int = 0
        private set


    init {
        fetchCardStatistics()
    }

    // Lấy dữ liệu thống kê từ card API
    private fun fetchCardStatistics() {
        viewModelScope.launch {
            try {
                val response = cardApiService.getCardStatistics(flashcardId)
                if (response.isSuccessful) {
                    val statisticMap = response.body() ?: emptyMap()
                    totalCard = statisticMap["totalCard"] ?: 0
                    newCard = statisticMap["newCard"] ?: 0
                    failCard = statisticMap["failCard"] ?: 0
                    hardCard = statisticMap["hardCard"] ?: 0
                    goodCard = statisticMap["goodCard"] ?: 0
                    easyCard = statisticMap["easyCard"] ?: 0
                } else {
                    Log.d("StatisticViewModel", "Failed to fetch statistics: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("StatisticViewModel", "Error fetching statistics", e)
            }
        }
    }
}