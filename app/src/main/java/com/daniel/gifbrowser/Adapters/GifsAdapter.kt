package com.daniel.gifbrowser.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daniel.gifbrowser.Domain.GifObject
import com.daniel.gifbrowser.R
import java.lang.StringBuilder
import java.net.URI
import java.net.URISyntaxException
import java.util.ArrayList

class GifsAdapter(gifs: ArrayList<GifObject>, context: Context, mOnItemClickListener: GifsAdapter.OnItemClickListener): RecyclerView.Adapter<GifsViewHolder>() {
    private val gifs: ArrayList<GifObject>? = gifs
    private val context = context
    private val mOnItemClickListener: GifsAdapter.OnItemClickListener? = mOnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(v:View, position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.gif_item, parent, false)
        return mOnItemClickListener?.let { GifsViewHolder(itemView, it) }!!
    }

    override fun getItemCount(): Int {
        return gifs!!.size
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val gif = gifs!![position]
        val imageGif = holder.imageGif
        val favIndicator = holder.favIndicator
        Glide.with(context).load(gif.images.original.url).into(imageGif)
        var uri: URI? = null
    }


}