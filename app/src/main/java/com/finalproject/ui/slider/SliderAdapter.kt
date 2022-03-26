package com.finalproject.ui.slider

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.finalproject.Home
import com.finalproject.R
import com.finalproject.utilities.LayoutUtils
import com.makeramen.roundedimageview.RoundedImageView

class SliderAdapter internal constructor(
    sliderItems: MutableList<SliderItem>,
    titlesList: MutableList<String>,
    viewPager2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val sliderItems: List<SliderItem>
    private val viewPager2: ViewPager2
    private val titlesList: List<String>

    init {
        this.sliderItems = sliderItems
        this.titlesList = titlesList
        this.viewPager2 = viewPager2
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: RoundedImageView = itemView.findViewById(R.id.imageSlide)
        val itemTitle: TextView = itemView.findViewById(R.id.tv_title_slider)

        init {
            val layoutUtils = LayoutUtils()
            val homeLayoutOptions = layoutUtils.getHomeLayoutClasses()
            imageView.setOnClickListener{
                    v: View ->
                val position = adapterPosition

                if (homeLayoutOptions[position + 1] != null) {
                    val intent = Intent(itemView.context, homeLayoutOptions[position + 1])
                    itemView.context.startActivity(intent)
                } else {
                    Toast.makeText(itemView.context, "You clicked on item #${position + 1}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun image(sliderItem: SliderItem) {
            imageView.setImageResource(sliderItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(sliderItems[position])
        holder.itemTitle.text = titlesList[position]

        if (position == sliderItems.size - 2) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    private val runnable = Runnable {
        sliderItems.addAll(sliderItems)
        titlesList.addAll(titlesList)
        notifyDataSetChanged()
    }
}