package com.kaboom.kidsmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kaboom.kidsmathgame.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerBinding: ActivityRegisterBinding


    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(registerBinding.root)

        registerBinding.btnRegister.setOnClickListener {
            val email = registerBinding.etEmail.text.toString()
            val password = registerBinding.etPassword.text.toString()
            signUpWithFirebase(email, password)


        }

        registerBinding.tvLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)

        }


    }

    fun signUpWithFirebase(email: String, password: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Invalid email format
            Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the password meets the required criteria (e.g., minimum length)
        if (password.length < 6) {
            Toast.makeText(
                applicationContext,
                "Password must be at least 6 characters long",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val confirmPassword = registerBinding.etPassword.text.toString()
        if (password != confirmPassword) {
            Toast.makeText(
                applicationContext,
                "Password and Confirm Password do not match",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "You have registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    registerBinding.btnRegister.isClickable = true
                } else {
                    // If registration fails, display a message to the user.
                    Toast.makeText(
                        applicationContext,
                        task.exception?.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    registerBinding.btnRegister.isClickable = true
                }
            }

}
}
