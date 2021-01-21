package com.android.appetisermasterdetail.network

import com.android.appetisermasterdetail.data.model.BaseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface AppleAPI {

    @GET("search")
    fun getSongList(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Observable<BaseData>

}