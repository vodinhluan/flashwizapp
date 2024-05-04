package com.example.flashwiz_fe.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.remote.CardApiService
import kotlinx.coroutines.launch

class StatisticViewModel(private val cardApiService: CardApiService, private val flashcardId: Int) : ViewModel() {
    val statisticData: MutableLiveData<Map<String, Int>?> = MutableLiveData()

    init {
        fetchCardStatistics()
    }

    // Lấy dữ liệu thống kê từ card API
    private fun fetchCardStatistics() {
        viewModelScope.launch {
            try {
                val response = cardApiService.getCardStatistics(flashcardId)
                Log.d("ID FlashCard ABCXYZ:", "FlashCard ID 123456: $flashcardId")
                Log.d("ID FlashCard ABCXYZ:", "Response ID 123456: $response")

                if (response.isSuccessful) {
                    statisticData.postValue(response.body())
                } else {
                    // Xử lý lỗi khi gọi API không thành công
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi có ngoại lệ xảy ra
            }
        }
    }
}
