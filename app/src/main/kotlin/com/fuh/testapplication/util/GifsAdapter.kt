package com.fuh.testapplication.util

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.BitmapRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.load.resource.transcode.BitmapToGlideDrawableTranscoder
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif
import kotlinx.android.synthetic.main.item_gif.view.*
import timber.log.Timber

/**
 * Created by Nick on 22.03.2017.
 */
class GifsAdapter(
        var gifs: MutableList<Gif>,
        val itemClick: (Context, Uri, BitmapRequestBuilder<Uri, GlideDrawable>, ImageView, Boolean) -> Unit
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
            val itemClick: (Context, Uri, BitmapRequestBuilder<Uri, GlideDrawable>, ImageView, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(gif: Gif) = with(gif) {
            var isPlaying = false
            val uri = Uri.parse(images.original.url)
            val thumbRequest = Glide
                    .with(itemView.ctx)
                    .load(uri)
                    .asBitmap()
                    .transcode(BitmapToGlideDrawableTranscoder(itemView.ctx), GlideDrawable::class.java)
                    .override(150, 150)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.thumb_image_error)
                    .placeholder(R.drawable.thumb_image_loading)
                    .fitCenter()


            thumbRequest.into(itemView.imageItemGifIcon)
            itemView.textItemGifTitle.text = slug
            itemView.setOnClickListener {
                itemClick(itemView.ctx, uri, thumbRequest,itemView.imageItemGifIcon, isPlaying)
                isPlaying = !isPlaying
            }
        }
    }
}