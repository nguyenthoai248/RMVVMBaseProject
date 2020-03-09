package com.equal.base.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

data class LoginDto(
    @SerializedName("token") val token: String,
    @SerializedName("userId") val userId: Long,
    @SerializedName("title") val title: String
)
