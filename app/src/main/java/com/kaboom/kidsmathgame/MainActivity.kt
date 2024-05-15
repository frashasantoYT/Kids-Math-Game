package com.kaboom.kidsmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kaboom.kidsmathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain : ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingMain.root)
        auth = Firebase.auth

        supportActionBar?.title = "Math Game"

        bindingMain.cardAddition.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "addition" )
            startActivity(intent)
        }


        bindingMain.cardMultiplication.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "multiplication" )
            startActivity(intent)
        }


        bindingMain.cardDivision.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "division" )
            startActivity(intent)
        }


        bindingMain.cardSubtraction.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "subtraction" )
            startActivity(intent)
        }






    }


}