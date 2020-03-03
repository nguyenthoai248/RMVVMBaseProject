package com.equal.base.common.utils

import javax.inject.Inject
import androidx.core.util.Pair

/**
 * Created by Thoai Nguyen on 3/3/20.
 */
class StringUtils @Inject internal constructor() {
    /**
     * Use to replace the placeholders for strings that use the format "text {{placeholder}} text".
     *
     * @param string        string to apply substitutions on
     * @param substitutions key value pairs (placeholder, substitutionString)
     * @return string with substitutions applied
     */
    fun applySubstitutionsToString(
        string: String,
        vararg substitutions: Pair<String, String>
    ): String {
        var primaryString = string
        for (substitution in substitutions) {
            substitution.second?.let {
                primaryString =
                    primaryString.replace("{{" + substitution.first + "}}", it)
            }
        }
        return primaryString
    }
}