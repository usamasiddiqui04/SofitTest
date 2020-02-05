package com.sofit.filemanager.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sofit.filemanager.R
import com.sofit.filemanager.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startTimer()
    }

    private fun startTimer() {
        lifecycleScope.launch {
            delay(SPLASH_TIMER)
            startActivity(HomeActivity.createIntent(this@SplashActivity))
            finish()
        }

    }

    companion object {
        const val SPLASH_TIMER: Long = 3000
    }

}