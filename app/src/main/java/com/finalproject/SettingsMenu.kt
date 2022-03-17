package com.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class SettingsMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_menu)

        val array_adapter: ArrayAdapter<*>
        val setting_options = mutableListOf(
            "Profile", "Appearance", "Functions", "Notifications", "Contact", "Support", "Home")
        val classe_options = mapOf(
            "Profile" to null,
            "Appearance" to null,
            "Functions" to null,
            "Notifications" to null,
            "Contact" to null,
            "Support" to null,
            "Home" to Home::class.java
        )
        val lv_menu = findViewById<ListView>(R.id.lv_settings)

        array_adapter = ArrayAdapter(this, R.layout.list_black_text, android.R.layout.simple_list_item_1, setting_options)
        lv_menu.adapter = array_adapter

        lv_menu.setOnItemClickListener(){ parent,view,position,id ->
            var option = parent.getItemAtPosition(position).toString()

            if (classe_options[option] != null) {
                val intent = Intent(this, classe_options[parent.getItemAtPosition(position).toString()])
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Does not exists an activity for ${option}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}