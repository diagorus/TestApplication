package com.fuh.testapplication.util

import android.content.Context
import android.net.Uri
import com.bumptech.glide.DrawableRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif

object GifUtils {

    fun getThumbGifRequest(ctx: Context, gif: Gif) = gif.images?.fixed_height_still?.let {
        return@let Glide
                .with(ctx)
                .load(Uri.parse(it.url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.thumb_image_error)
                .placeholder(R.drawable.thumb_image_loading)
                .override(it.width, it.height)
                .fitCenter()
    }

    fun getActualGifRequest(
            ctx: Context,
            gif: Gif,
            thumbRequest: DrawableRequestBuilder<Uri>
    ) = gif.images?.fixed_height?.let {

        return@let Glide
                .with(ctx)
                .load(it.url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.thumb_image_loading)
                .error(R.drawable.thumb_image_error)
                .thumbnail(thumbRequest)
                .override(it.width, it.height)
                .dontAnimate()
    }
}