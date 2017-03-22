package com.fuh.testapplication.util

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif
import kotlinx.android.synthetic.main.item_gif.view.*
import timber.log.Timber

/**
 * Created by Nick on 22.03.2017.
 */
class GifsAdapter(
        var gifs: MutableList<Gif>,
        val itemClick: (Gif) -> Unit
) : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = gifs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_gif, parent, false)
        return ViewHolder(view, itemClick)
    }

    class ViewHolder(
            itemView: View,
            val itemClick: (Gif) -> Unit
    ): RecyclerView.ViewHolder(itemView) {
        fun bind(gif: Gif) = with(gif) {
            Timber.i(this.toString())
            Glide.with(itemView.ctx)
                    .load(images.original.url)
                    .asBitmap()
                    .error(R.drawable.thumb_image_error)
                    .placeholder(R.drawable.thumb_image_loading)
                    .into(itemView.imageItemGifIcon)
            itemView.textItemGifTitle.text = slug
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}