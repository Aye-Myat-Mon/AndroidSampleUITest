package com.android.amm.androidsampleuitest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_image_item.view.*

/**
 * Created by ayemyatmon on 18,April,2022
 */
class ImageAdapter(private val data: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_image_item, parent, false)
        return ImageViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) {
            0
        } else {
            data.size
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            image: String
        ) {
            Glide.with(itemView)
                .load(image)
                .skipMemoryCache(true)
                .into(itemView.iv_image)
        }
    }
}