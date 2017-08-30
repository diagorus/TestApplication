package com.fuh.testapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by Nick on 22.03.2017.
 */
data class GifsEnvelope(
        @SerializedName("data")
        val data: List<Gif>,
        @SerializedName("pagination")
        val pagination: Pagination
)

data class Pagination(
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("offset")
        val offset: Int
)

open class Gif(
        @PrimaryKey
        @Expose
        @SerializedName("id")
        open var id: String? = null,
        @Expose
        @SerializedName("slug")
        open var slug: String? = null,
        @Expose
        @SerializedName("images")
        open var images: Images? = null
) : RealmObject()

open class Images(
        @Expose
        @SerializedName("fixed_height")
        open var fixedHeight: GifImage? = null,
        @Expose
        @SerializedName("fixed_height_still")
        open var fixedHeightStill: GifImage? = null
) : RealmObject()

open class GifImage(
        @Expose
        @SerializedName("url")
        open var url: String? = null,
        @Expose
        @SerializedName("width")
        open var width: Int = 0,
        @Expose
        @SerializedName("height")
        open var height: Int = 0
) : RealmObject()