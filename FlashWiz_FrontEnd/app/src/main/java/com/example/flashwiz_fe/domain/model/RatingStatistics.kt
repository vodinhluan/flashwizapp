package com.example.flashwiz_fe.domain.model

data class RatingStatistics(
    var newCount: Int = 0,
    var failCount: Int = 0,
    var hardCount: Int = 0,
    var goodCount: Int = 0,
    var easyCount: Int = 0
)