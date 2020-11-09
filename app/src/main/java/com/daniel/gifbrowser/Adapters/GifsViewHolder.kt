package com.daniel.gifbrowser.Adapters

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniel.gifbrowser.R

/**
 * ViewHolder Class for the RecyclerViewAdapter
 */

class GifsViewHolder(itemView: View,onItemClickListener: GifsAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView){

    var favIndicator : ImageButton
    var imageGif: ImageView
    var onItemClickListener: GifsAdapter.OnItemClickListener

    init {
        imageGif = itemView.findViewById(R.id.image_gif)
        favIndicator = itemView.findViewById(R.id.fav_indicator)
        this.onItemClickListener = onItemClickListener
        favIndicator.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(it,adapterPosition)
        })
    }
}