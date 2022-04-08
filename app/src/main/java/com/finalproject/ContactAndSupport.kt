package com.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.finalproject.databinding.ActivityContactAndSupportBinding
import com.finalproject.databinding.ActivityMainBinding

class ContactAndSupport : AppCompatActivity() {

    private lateinit var binding: ActivityContactAndSupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactAndSupportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSend.setOnClickListener { send() }
    }

    private fun send() {
        val info = binding.setinfo.text.toString()

        if(info != ""){
            Toast.makeText(
                this,
                "Message send succesfully",
                Toast.LENGTH_LONG).show()
                val intent = Intent(this, SettingsMenu::class.java)
                startActivity(intent)
        }else{
            Toast.makeText(
                this,
                "There is no message to send",
                Toast.LENGTH_LONG).show()
        }
    }
}