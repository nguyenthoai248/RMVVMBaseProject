package com.equal.base.presentation.recyclerview

import com.google.auto.value.AutoValue

/**
 * Created by Thoai Nguyen on 3/4/20.
 */

@AutoValue
abstract class DisplayableItem<T> {

    abstract fun type(): Int

    abstract fun model(): T

    @AutoValue.Builder
    abstract class Builder<T> {

        abstract fun type(type: Int): Builder<T>

        abstract fun model(model: T): Builder<T>

        abstract fun build(): DisplayableItem<T>
    }

    companion object {

        fun <T> builder(): Builder<T> {
            return AutoValue_DisplayableItem.Builder()
        }

        fun toDisplayableItem(model: Any, type: Int): DisplayableItem<*> {
            return DisplayableItem.builder<Any>().type(type).model(model).build()
        }
    }
}