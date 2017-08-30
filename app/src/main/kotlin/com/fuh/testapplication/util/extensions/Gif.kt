package com.fuh.testapplication.util.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * Created by lll on 30.08.2017.
 */
fun Gif.getThumbRequestFromStorage(ctx: Context): RequestBuilder<Drawable>? {
    val fixedHeightImage = images?.fixedHeight ?:
            throw IllegalArgumentException("No fixedHeight image found")

    val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.thumb_image_loading)
            .error(R.drawable.thumb_image_error)
            .override(fixedHeightImage.width, fixedHeightImage.height)
            .dontAnimate()

    return Glide
            .with(ctx)
            .load(fixedHeightImage.url)
            .apply(options)
}

fun Gif.getRequestFromStorage(
        ctx: Context,
        thumbRequest: RequestBuilder<Drawable>
): RequestBuilder<Drawable>? {
    val fixedHeightImage = images?.fixedHeight ?:
            throw IllegalArgumentException("No fixedHeight image found")

    val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.thumb_image_loading)
            .error(R.drawable.thumb_image_error)
            .override(fixedHeightImage.width, fixedHeightImage.height)

    return Glide
            .with(ctx)
            .load(fixedHeightImage.url)
            .thumbnail(thumbRequest)
            .apply(options)
}

fun Gif.getThumbRequest(ctx: Context): RequestBuilder<Drawable>?
        = images?.fixedHeightStill?.let {
    val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.thumb_image_error)
            .placeholder(R.drawable.thumb_image_loading)
            .override(it.width, it.height)
            .fitCenter()

    return Glide
            .with(ctx)
            .load(Uri.parse(it.url))
            .apply(requestOptions)
}

fun Gif.getRequest(ctx: Context, thumbRequest: RequestBuilder<Drawable>): RequestBuilder<Drawable>?
        = images?.fixedHeight?.let {
    val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.drawable.thumb_image_error)
            .placeholder(R.drawable.thumb_image_loading)
            .override(it.width, it.height)

    return@let Glide
            .with(ctx)
            .load(it.url)
            .apply(requestOptions)
            .thumbnail(thumbRequest)
}