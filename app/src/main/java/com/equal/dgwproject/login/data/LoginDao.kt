package com.equal.dgwproject.login.data

import androidx.paging.DataSource
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.reactive.DataSubscription
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

class LoginDao(database: Database) {

    private var dao: Box<LoginData> = database.boxStore.boxFor()

//    fun saveLogins(logins: List<LoginData>) {
//        logins.forEach {
//            dao.query().equal(LoginData_.id, it.id).build().findFirst()?.apply {
//                it._id = _id
//            }
//        }
//        dao.put(logins)
//    }

    fun clear() = dao.removeAll()

    fun count(): Int = dao.count().toInt()

    fun getDataSourceFactory(): DataSource.Factory<Int, LoginData> = object : DataSource.Factory<Int, LoginData>() {

        private val dataHashCode = AtomicInteger(0)
        private var subscription: DataSubscription? = null

        override fun create(): DataSource<Int, LoginData> {
            subscription?.apply { cancel() }
            val query = dao.query().build()
            val dataSource = LimitOffsetDataSource(query, dataHashCode)
            subscription = query.subscribe().weak().observer(dataSource)
            return dataSource
        }
    }
}