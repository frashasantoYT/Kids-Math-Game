package com.kaboom.kidsmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val title = findViewById<TextView>(R.id.textViewSplash)

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.anim)
        title.startAnimation(animation)


        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish WelcomeActivity if needed
        }, 4000) // Delay for 3 seconds (5000 milliseconds)
    }



    }

