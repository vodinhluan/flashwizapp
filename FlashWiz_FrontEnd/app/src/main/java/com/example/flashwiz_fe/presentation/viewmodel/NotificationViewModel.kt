package com.example.flashwiz_fe.presentation.viewmodel

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,

) : ViewModel() {
    fun showSimpleNotification() {
        notificationManager.notify(1, notificationBuilder.build())
    }

    //    fun updateSimpleNotification() {
//        notificationManager.notify(1, notificationBuilder
//            .setContentTitle("NEW TITLE")
//            .build()
//        )
//    }
//
//    fun cancelSimpleNotification() {
//        notificationManager.cancel(1)
//    }
}

