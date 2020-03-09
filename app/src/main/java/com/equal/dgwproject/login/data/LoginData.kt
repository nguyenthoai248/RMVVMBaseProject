package com.equal.dgwproject.login.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Unique

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

@Entity
data class LoginData(
    @Id var _id: Long = 0,
    @Unique val id: Long,
    val token: String,
    val userId: Long,
    val title: String
)