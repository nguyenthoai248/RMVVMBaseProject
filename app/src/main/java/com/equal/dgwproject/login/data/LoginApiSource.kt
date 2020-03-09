package com.equal.dgwproject.login.data

import com.equal.base.data.Api
import com.equal.base.data.LoginDto
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Created by Thoai Nguyen on 3/9/20.
 */
class LoginApiSource(private val api: Api) : LoginApiDataSource {
    override fun login(userName: String, pass: String): Single<LoginDto> {
        return api.login(RequestBody.create(MediaType.parse(""), ""))
    }


//    override fun getAlbums(offset: Int, limit: Int): Single<AlbumsEntity> = api.getAlbums(offset, limit)
//        .map { AlbumsEntity(it.map(AlbumDto::map), /* Hard coded in the sample app. Don't do this in product. */100) }
}