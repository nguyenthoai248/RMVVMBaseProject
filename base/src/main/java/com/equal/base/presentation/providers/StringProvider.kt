package com.equal.base.presentation.providers

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.util.Pair
import com.equal.base.common.utils.StringUtils
import com.equal.base.injection.qualifiers.ForApplication
import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
/**
 * Provides access to string resources to classes that have not access to Context (publishers, mappers, etc.)
 */
class StringProvider @Inject internal constructor(
    @param:ForApplication private val context: Context, private val stringUtils: StringUtils
) {
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    /**
     * Use to replace the placeholders for strings that use the format "text {{placeholder}} text".
     *
     * @param stringResId   string resource id
     * @param substitutions substitutions
     * @return string
     */
    fun getStringAndApplySubstitutions(@StringRes stringResId: Int, vararg substitutions: Pair<String, String>): String {
        return stringUtils.applySubstitutionsToString(
            context.getString(stringResId),
            *substitutions
        )
    }

}