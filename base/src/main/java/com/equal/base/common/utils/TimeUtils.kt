package com.equal.base.common.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/3/20.
 */
class TimeUtils @Inject internal constructor() {
    /**
     * Formats the passed date string following the passed format pattern.
     *
     * @param dateString    the string of the date to format
     * @param formatPattern the pattern to format date string
     */
    fun formatDateString(dateString: String, formatPattern: String): String {
        val sdf =
            SimpleDateFormat(formatPattern, Locale.ENGLISH)
        val date = parseStringToDateWithPassedPattern(dateString, "yyyy-MM-dd")
        return sdf.format(date)
    }

    /**
     * Parses the passed string to a date with the passed format.
     *
     * @param dateString    the string of the date to parse
     * @param formatPattern the pattern to format date string
     * @throws RuntimeException if there is any problems while parsing
     */
    fun parseStringToDateWithPassedPattern(
        dateString: String,
        formatPattern: String
    ): Date {
        val sdf =
            SimpleDateFormat(formatPattern, Locale.GERMAN)
        return try {
            sdf.parse(dateString)
        } catch (e: ParseException) {
            throw RuntimeException("Error formatting $dateString with pattern $formatPattern to Date")
        }
    }
}