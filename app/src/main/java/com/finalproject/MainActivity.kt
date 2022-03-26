package com.finalproject

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.finalproject.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btAuthenticate.setOnClickListener {
            login();
        }
    }

    private fun login() {
        val email = binding.tvEmail.text.toString()
        val password = binding.tvPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                    task->
                if (task.isSuccessful) {
                    Log.d("Sign in user", "Authenticated")
                    val user = auth.currentUser
                    update(user)
                } else {
                    Toast.makeText(baseContext, "Failed", Toast.LENGTH_LONG).show()
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

    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        update(user)
    }
}