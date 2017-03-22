package com.fuh.testapplication.model

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Nick on 22.03.2017.
 */
interface GiphyApi {

    @GET("v1/gifs/search?api_key=dc6zaTOxFJmzC")
    fun search(@Query("q") query: String,
               @Query("offset") offset: Int,
               @Query("limit") limit: Int
    ): Observable<GifsEnvelope>
}