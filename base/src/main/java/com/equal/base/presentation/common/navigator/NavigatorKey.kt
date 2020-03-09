package com.equal.base.presentation.common.navigator

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Thoai Nguyen on 3/9/20.
 */
@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class NavigatorKey(val value: KClass<out Navigator>)