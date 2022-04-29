package com.finalproject

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.finalproject.ui.slider.SliderAdapter
import com.finalproject.ui.slider.SliderItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class Home : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var titleTextView: TextView
    private lateinit var fbAdd: FloatingActionButton
    private val sliderHandler = Handler()
    private var userName = " User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val user = Firebase.auth.currentUser

        if (user != null && user.displayName.toString().length > 1) {
            userName = " ${user.displayName.toString()}"
        }

        titleTextView = findViewById(R.id.tv_greetings)
        viewPager2 = findViewById(R.id.vp2_image_slider)

        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.drawable.sample_image1))
        sliderItems.add(SliderItem(R.drawable.sample_image2))
        sliderItems.add(SliderItem(R.drawable.sample_image3))
        sliderItems.add(SliderItem(R.drawable.sample_image4))

        val titlesList: MutableList<String> = ArrayList()
        titlesList.add("BARES")
        titlesList.add("RESERVATIONS")
        titlesList.add("SETTINGS")
        titlesList.add("PROFILE")

        viewPager2.adapter = SliderAdapter(
            sliderItems,
            titlesList,
            viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer {
                page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })

        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        if (hour in 7..12) {
            titleTextView.text = getString(R.string.good_morning) + userName
        } else if (hour in 13..18) {
            titleTextView.text = getString(R.string.good_afternoon) + userName
        } else {
            titleTextView.text = getString(R.string.good_night) + userName
        }
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}