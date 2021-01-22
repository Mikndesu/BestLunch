package com.mikn.bestlunch.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.mikn.bestlunch.R
import com.mikn.bestlunch.model.Rest

class CustomInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
    private val window: View = (context as Activity).layoutInflater.inflate(R.layout.info_window, null)
    private var restList: MutableList<Rest> = mutableListOf()

    override fun getInfoWindow(marker: Marker): View {
        render(marker, window)
        return window
    }

    override fun getInfoContents(marker: Marker): View? {
        render(marker, window)
        return null
    }

    fun updateList(list: MutableList<Rest>) {
        restList = list
    }

    private fun render(marker: Marker, view: View) {
        view.findViewById<ImageView>(R.id.fujiImageView).setImageResource(R.drawable.ic_launcher_background)
        var title: String? = marker.title
        val titleUi = view.findViewById<TextView>(R.id.title)
        if(title != null) {
            titleUi.text = title
        } else {
            titleUi.text = ""
        }

        val snippet:String? = marker.snippet
        val snippetUi = view.findViewById<TextView>(R.id.snippet)
        if(snippet != null) {
            snippetUi.text = snippet
        } else {
            snippetUi.text = ""
        }
    }
}