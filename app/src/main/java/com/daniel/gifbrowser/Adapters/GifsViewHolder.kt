package com.daniel.gifbrowser.Adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniel.gifbrowser.R

class GifsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    var favIndicator : ImageView
    var imageGif: ImageView
    init {
        imageGif = itemView.findViewById(R.id.image_gif)
        favIndicator = itemView.findViewById(R.id.fav_indicator)
    }
}