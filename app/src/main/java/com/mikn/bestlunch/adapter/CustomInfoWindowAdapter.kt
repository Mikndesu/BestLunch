package com.mikn.bestlunch.adapter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.mikn.bestlunch.R
import com.mikn.bestlunch.model.Shop
import kotlinx.android.synthetic.main.info_window.view.*

class CustomInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
    private val window: View = (context as Activity).layoutInflater.inflate(R.layout.info_window, null)
    private var restList: List<Shop> = mutableListOf()

    override fun getInfoWindow(marker: Marker): View {
        render(marker, window)
        return window
    }

    override fun getInfoContents(marker: Marker): View? {
        val isRedraw = marker != null && marker.isInfoWindowShown
        if (isRedraw) marker.showInfoWindow()
        render(marker, window)
        return null
    }

    fun updateList(list: List<Shop>) {
        restList = list
    }

    private fun render(marker: Marker, view: View) {
        val shop = restList[marker.tag as Int]

        Glide.with(context)
            .load(shop.photo.mobile.l)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    getInfoContents(marker)
                    return false
                }
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    if(DataSource.MEMORY_CACHE != p3) {
                        Handler().postDelayed({
                            getInfoContents(marker)
                        }, 100)
                    }
                    return false
                }
            })
            .into(window.image)

        view.title.text = shop.name
        Log.d("url", shop.urls.toString())
    }
}
