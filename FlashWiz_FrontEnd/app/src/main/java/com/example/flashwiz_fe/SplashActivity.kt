package com.example.flashwiz_fe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the splash screen layout
        setContentView(R.layout.splash_screen)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim)
        findViewById<View>(R.id.splash_root_view).startAnimation(fadeIn) // replace with your root view id


        // Start the main activity after a delay
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, com.example.flashwiz_fe.MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY: Long = 3000 // 3 seconds
    }
}
