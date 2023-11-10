package com.app.weather_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splas)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}