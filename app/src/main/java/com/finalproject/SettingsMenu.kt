package com.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SettingsMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_menu)

        val arrayAdapter: ArrayAdapter<*>
        val settingOptions = arrayOf(
            "Profile", "Appearance", "Functions", "Notifications", "Contact", "Support", "Home")
        val classOptions = mapOf(
            "Profile" to null,
            "Appearance" to null,
            "Functions" to null,
            "Notifications" to null,
            "Contact" to null,
            "Support" to null,
            "Home" to Home::class.java
        )

        val lvMenu = findViewById<ListView>(R.id.lv_settings)
        val svMenu = findViewById<SearchView>(R.id.searchView)

        arrayAdapter = ArrayAdapter(this, R.layout.setting_menu_item, R.id.tv, settingOptions)
        lvMenu.adapter = arrayAdapter

        svMenu.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                svMenu.clearFocus()
                if (settingOptions.contains(query)) {
                    arrayAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                arrayAdapter.filter.filter(query)
                return false
            }

        })

        lvMenu.setOnItemClickListener{ parent,view,position,id ->
            val option = parent.getItemAtPosition(position).toString()

            if (classOptions[option] != null) {
                val intent = Intent(this, classOptions[parent.getItemAtPosition(position).toString()])
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Does not exists an activity for $option",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}