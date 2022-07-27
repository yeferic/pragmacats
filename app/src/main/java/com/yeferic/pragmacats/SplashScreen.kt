package com.yeferic.pragmacats

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.yeferic.pragmacats.presentation.catbreeds.screens.CatBreedsView
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_splash)

        scope.launch {
            delay(3000)
            val intent = Intent(this@SplashScreen, CatBreedsView::class.java)
            startActivity(intent)
            finish()
        }
    }
}