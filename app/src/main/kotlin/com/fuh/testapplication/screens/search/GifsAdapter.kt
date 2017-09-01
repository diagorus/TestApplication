package com.fuh.testapplication.screens.search

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.util.extensions.ctx
import com.fuh.testapplication.util.extensions.getRequest
import com.fuh.testapplication.util.extensions.getThumbRequest
import kotlinx.android.synthetic.main.item_gif.view.*

/**
 * Created by Nick on 22.03.2017.
 */
class GifsAdapter(
        private val itemClick: (ImageView, RequestBuilder<Drawable>, RequestBuilder<Drawable>, Boolean) -> Unit,
        private val itemLongClick: (Gif) -> Boolean,
        initialValues: List<Gif> = listOf()
) : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    private var gifs: MutableList<Gif> = mutableListOf<Gif>().apply { addAll(initialValues) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_gif, parent, false)
        return ViewHolder(view, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifs[position])
    }

    override fun getItemCount(): Int = gifs.size

    fun setGifs(gifs: List<Gif>) = with(this.gifs) {
        clear()
        addAll(gifs)
    }

    fun addAllToGifs(gifs: List<Gif>) = this.gifs.addAll(gifs)

    class ViewHolder(
            itemView: View,
            private val itemClick: (ImageView, RequestBuilder<Drawable>, RequestBuilder<Drawable>, Boolean) -> Unit,
            private val itemLongClick: (Gif) -> Boolean
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(gif: Gif) = with(gif) {
            val thumbRequest = getThumbRequest(itemView.ctx)!!
            val originalRequest = getRequest(itemView.ctx, thumbRequest)!!
            var isPlaying = false

            thumbRequest.into(itemView.imageItemGifIcon)

            itemView.setOnClickListener {
                itemClick(itemView.imageItemGifIcon, thumbRequest, originalRequest, isPlaying)
                isPlaying = !isPlaying
            }
            itemView.setOnLongClickListener {
                itemLongClick(this)
            }
        }
    }
}