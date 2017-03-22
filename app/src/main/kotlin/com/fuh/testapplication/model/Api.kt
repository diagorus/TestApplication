package com.fuh.testapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nick on 22.03.2017.
 */
data class GifsEnvelope(
        @SerializedName("data") val data: List<Gif>,
        @SerializedName("pagination") val pagination: Pagination)

data class Gif(
        @SerializedName("id") val id: String,
        @SerializedName("slug") val slug: String,
        @SerializedName("url") val url: String,
        @SerializedName("images") val images: Images)

data class Pagination(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("offset") val offset: Int)

data class Images(@SerializedName("original") val original: GifImage)

data class GifImage(@SerializedName("url") val url: String)