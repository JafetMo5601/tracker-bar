package com.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.finalproject.utilities.LayoutUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val layoutUtilsInstance = LayoutUtils()
        val arrayAdapter: ArrayAdapter<*>
        val classOptions = layoutUtilsInstance.getProfileLayoutClasses()
        val profileOptions = classOptions.keys.toList()
        val lvMenu = findViewById<ListView>(R.id.lv_profile_options)
        arrayAdapter = ArrayAdapter(this, R.layout.profile_menu_items, R.id.tv, profileOptions)
        lvMenu.adapter = arrayAdapter


        lvMenu.setOnItemClickListener { parent, _, position, _ ->
            val option = parent.getItemAtPosition(position).toString()

            if (option == "COVID-19 Guidelines") {
                val intent = Intent(this, CovidGuideLines::class.java)
                startActivity(intent)
            } else if (option == "Favorites") {
                val intent = Intent(this, ProfilePage::class.java)
                startActivity(intent)
            } else {
                if (classOptions[option] != null) {
                    val intent =
                        Intent(this, classOptions[parent.getItemAtPosition(position).toString()])
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "Does not exists an activity for $option",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        credentials()
    }

    private fun credentials() {
        val tvName: TextView = findViewById(R.id.user_name)
        val tvEmail: TextView = findViewById(R.id.user_email)
        val image: ImageView = findViewById(R.id.ProfilePhoto)

        val usuario = Firebase.auth.currentUser

        tvName.text = usuario?.displayName
        tvEmail.text = usuario?.email
        val rutaFoto = usuario?.photoUrl.toString()
        if (rutaFoto.isNotEmpty()) {
            Glide.with(this)
                .load(rutaFoto)
                .circleCrop()
                .into(image)
        }
    }

}
