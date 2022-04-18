package com.android.amm.androidsampleuitest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.amm.androidsampleuitest.models.RoomInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_room_item.view.*

/**
 * Created by ayemyatmon on 18,April,2022
 */
class RoomTypeAdapter(private val data: List<RoomInfo>, private val isRoom: Boolean) :
    RecyclerView.Adapter<RoomTypeAdapter.RoomTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_room_item, parent, false)
        return RoomTypeViewHolder(
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

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        holder.bind(data[position], isRoom)
    }

    class RoomTypeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            room: RoomInfo,
            isRoom: Boolean
        ) {
            Glide.with(itemView)
                .load(room.image)
                .skipMemoryCache(true)
                .into(itemView.iv_image)

            if (isRoom) {
                itemView.cl_by_room.visibility = View.VISIBLE
                itemView.cl_by_rate.visibility = View.GONE
            } else {
                itemView.cl_by_room.visibility = View.GONE
                itemView.cl_by_rate.visibility = View.VISIBLE
            }
        }
    }
}