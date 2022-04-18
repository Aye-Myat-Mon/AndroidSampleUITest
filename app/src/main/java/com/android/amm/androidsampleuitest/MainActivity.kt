package com.android.amm.androidsampleuitest

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.amm.androidsampleuitest.models.RoomInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab_layout.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var imageAdapter: ImageAdapter
    lateinit var roomTypeAdapter: RoomTypeAdapter

    val IMAGE_LIST = listOf(
        "https://image.tmdb.org/t/p/w185/6nhwr1LCozBiIN47b8oBEomOADm.jpg",
        "https://image.tmdb.org/t/p/w185/ejXBuNLvK4kZ7YcqeKqUWnCxdJq.jpg",
        "https://image.tmdb.org/t/p/w185/6nhwr1LCozBiIN47b8oBEomOADm.jpg",
        "https://image.tmdb.org/t/p/w185/7qop80YfuO0BwJa1uXk1DXUUEwv.jpg",
        "https://image.tmdb.org/t/p/w185/6nhwr1LCozBiIN47b8oBEomOADm.jpg"
    )

    val ROOM_ITEM_LIST = listOf(
        RoomInfo("https://image.tmdb.org/t/p/w185/6nhwr1LCozBiIN47b8oBEomOADm.jpg"),
        RoomInfo("https://image.tmdb.org/t/p/w185/ejXBuNLvK4kZ7YcqeKqUWnCxdJq.jpg"),
        RoomInfo("https://image.tmdb.org/t/p/w185/6nhwr1LCozBiIN47b8oBEomOADm.jpg"),
        RoomInfo("https://image.tmdb.org/t/p/w185/7qop80YfuO0BwJa1uXk1DXUUEwv.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareRecycler()

        item1.setOnClickListener(this)
        item2.setOnClickListener(this)
    }

    private fun prepareRecycler() {
        imageAdapter = ImageAdapter(IMAGE_LIST)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        linearLayoutManager.scrollToPositionWithOffset(1, 80)
        rv_imageRecycler.adapter = imageAdapter
        rv_imageRecycler.layoutManager = linearLayoutManager
        rv_imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position: Int = getCurrentItem()
                    tv_see_all.text = "See All ${position+1}/${imageAdapter.itemCount}"
                }
            }
        })
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_imageRecycler)


        val llManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        linearLayoutManager.scrollToPositionWithOffset(1, 80)
        rv_room_type.layoutManager = llManager
        setAdapter(true)
    }

    private fun getCurrentItem(): Int {
        return (rv_imageRecycler.layoutManager as LinearLayoutManager)
            .findFirstVisibleItemPosition()
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.item1) {
            select.animate().x(0f).duration = 100
            setAdapter(true)
        } else if (view?.id == R.id.item2) {
            val size: Int = item2.width
            select.animate().x(size.toFloat()).duration = 100
            setAdapter(false)
        }
    }

    private fun setAdapter(isRoom: Boolean) {
        roomTypeAdapter = RoomTypeAdapter(ROOM_ITEM_LIST, isRoom)
        rv_room_type.adapter = roomTypeAdapter
    }
}