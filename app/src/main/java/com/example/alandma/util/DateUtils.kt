// AlAndMa/app/src/main/java/com/example/alandma/util/DateUtils.kt
package com.example.alandma.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun formatDate(millis: Long): String {
        val date = Date(millis)
        return dateFormatter.format(date)
    }

    // Parse "yyyy-MM-dd" into epoch millis
    fun parseDateToMillis(dateString: String): Long {
        val date = dateFormatter.parse(dateString)
        return date?.time ?: System.currentTimeMillis()
    }
}
