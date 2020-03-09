package com.equal.base.presentation.common.navigator

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

@Singleton
@Suppress("UNCHECKED_CAST")
class NavigatorFactoryImpl
@Inject constructor(
    private val creators: Map<Class<out Navigator>, @JvmSuppressWildcards Provider<Navigator>>
) : NavigatorFactory {

    override fun <T : Navigator> create(clazz: Class<T>): T {
        val creator =
            creators[clazz] ?: creators.asIterable().firstOrNull { clazz.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown Navigator class $clazz")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}