package com.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.finalproject.databinding.ActivityMainBinding
import com.finalproject.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth


        binding.btRegister.setOnClickListener { register() }

        binding.btSignin.setOnClickListener { signInPage() }
    }

    private fun signInPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun register(){
        val email = binding.emailTv.text.toString()
        val clave = binding.tvPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("Authenticating", "User registered")
                    val user = auth.currentUser
                    Toast.makeText(this, "User registered succesfully", Toast.LENGTH_SHORT).show()
                    user?.sendEmailVerification()
                    update(user)
                } else {
                    Log.d("Authenticating", "Register failed")
                    update(null)
                }
            }
    }

    private fun update(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    /*private fun update() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }*/
}