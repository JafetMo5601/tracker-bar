package com.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.finalproject.utilities.LayoutUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.reflect.typeOf

class SettingsMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_menu)

        val layoutUtilsInstance = LayoutUtils()
        val arrayAdapter: ArrayAdapter<*>
        val classOptions = layoutUtilsInstance.getSettingLayoutClasses()
        val settingOptions = classOptions.keys.toList()
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

        lvMenu.setOnItemClickListener{ parent,_,position,_ ->
            val option = parent.getItemAtPosition(position).toString()

            if (option == "Sign out") {
                Firebase.auth.signOut()
                finish()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
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
}