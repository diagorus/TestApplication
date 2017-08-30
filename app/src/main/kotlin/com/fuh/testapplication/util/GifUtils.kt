package com.fuh.testapplication.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif

object GifUtils {

//    fun getThumbGifFromStorage(ctx: Context, gif: Gif) = gif.images?.fixedHeight?.let {
//        return@let Glide
//                .with(ctx)
//                .load(it.url)
//                .asBitmap()
//                .transcode(BitmapToGlideDrawableTranscoder(ctx), GlideDrawable::class.java)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.thumb_image_loading)
//                .error(R.drawable.thumb_image_error)
//                .override(it.width, it.height)
//                .dontAnimate()
//    }
//
//    fun getActualGifFromStorage(
//            ctx: Context,
//            gif: Gif,
//            thumbRequest: BitmapRequestBuilder<String, GlideDrawable>
//    ) = gif.images?.fixedHeight?.let {
//
//        return@let Glide
//                .with(ctx)
//                .load(it.url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .placeholder(R.drawable.thumb_image_loading)
//                .error(R.drawable.thumb_image_error)
//                .thumbnail(thumbRequest)
//                .override(it.width, it.height)
//                .dontAnimate()
//    }



//    fun getThumbGifRequest(ctx: Context, gif: Gif) = gif.images?.fixedHeightStill?.let {
//        return@let Glide
//                .with(ctx)
//                .load(Uri.parse(it.url))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.drawable.thumb_image_error)
//                .placeholder(R.drawable.thumb_image_loading)
//                .override(it.width, it.height)
//                .fitCenter()
//    }
//
//    fun getActualGifRequest(
//            ctx: Context,
//            gif: Gif,
//            thumbRequest: DrawableRequestBuilder<Uri>
//    ) = gif.images?.fixedHeight?.let {
//
//        return@let Glide
//                .with(ctx)
//                .load(it.url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .placeholder(R.drawable.thumb_image_loading)
//                .error(R.drawable.thumb_image_error)
//                .thumbnail(thumbRequest)
//                .override(it.width, it.height)
//                .dontAnimate()
//    }
}