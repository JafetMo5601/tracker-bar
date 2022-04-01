package com.finalproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.finalproject.databinding.ActivityMainBinding
import com.finalproject.databinding.ActivityResetPasswordBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetPassword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btReset.setOnClickListener { resetPassword() }

        binding.btSignin.setOnClickListener { signInPage() }

        binding.btSignup.setOnClickListener { singUpPage() }
    }

    private fun resetPassword() {
        if(binding.tvEmail.text.toString().isEmpty()){
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.tvEmail.text.toString()).matches()){
            return
        }

        Firebase.auth.sendPasswordResetEmail(binding.tvEmail.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email send succesfully", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signInPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun singUpPage() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}