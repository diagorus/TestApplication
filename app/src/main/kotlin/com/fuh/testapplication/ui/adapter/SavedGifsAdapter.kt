package com.fuh.testapplication.ui.adapter

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.util.GifUtils
import com.fuh.testapplication.util.extensions.ctx
import com.fuh.testapplication.util.extensions.getRequestFromStorage
import com.fuh.testapplication.util.extensions.getThumbRequest
import com.fuh.testapplication.util.extensions.getThumbRequestFromStorage
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_gif.view.*

/**
 * Created by Nick on 26.03.2017.
 */
class SavedGifsAdapter(private val dataChangeListener: RealmChangeListener<RealmResults<Gif>>,
                       private val itemClick: (ImageView, RequestBuilder<Drawable>, RequestBuilder<Drawable>, Boolean) -> Unit,
                       private val itemLongClickListener: (Gif) -> Boolean,
                       var gifs: RealmResults<Gif>? = null
) : RecyclerView.Adapter<SavedGifsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_gif, parent, false)
        return ViewHolder(view, itemClick, itemLongClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifs!![position])
    }

    override fun getItemCount(): Int = gifs?.size ?: 0

    fun setSavedGifs(data: RealmResults<Gif>) {
        gifs = data
        gifs!!.addChangeListener(dataChangeListener)
        notifyDataSetChanged()
    }

    class ViewHolder(
            itemView: View,
            private val itemClick: (ImageView, RequestBuilder<Drawable>, RequestBuilder<Drawable>, Boolean) -> Unit,
            private val itemLongClick: (Gif) -> Boolean
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(gif: Gif) = with(gif) {
            val thumbRequest = getThumbRequestFromStorage(itemView.ctx)!!
            val originalRequest = getRequestFromStorage(itemView.ctx, thumbRequest)!!
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