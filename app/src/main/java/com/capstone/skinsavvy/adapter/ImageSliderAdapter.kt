package com.capstone.skinsavvy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.skinsavvy.R
import com.capstone.skinsavvy.data.ImageSliderItem

class ImageSliderAdapter(private val context : Context, private val images:List<ImageSliderItem>):
    RecyclerView.Adapter<ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun getItemCount() = Int.MAX_VALUE

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        val actualPosition = position % images.size // Handle infinite scroll (optional)
        val imageItem = images[actualPosition]
        Glide.with(context).load(imageItem.imageUrl).into(holder.imageView)

    }
}
class ImageSliderViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.image_view)
}