package com.equal.base.data

import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Thoai Nguyen on 3/9/20.
 */
interface Api {
    @POST("loginapi")
    fun login(@Body body: RequestBody
    ): Single<LoginDto>

//    @GET("photos")
//    fun getPhotos(): Single<List<PhotoDto>>
}