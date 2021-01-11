package com.bombadu.mytesting.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Util {

    fun getCurrentDate(): String {
        val currentDate = LocalDateTime.now()
        return currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }

    fun getCurrentTime(): String {
        val currentTime = LocalDateTime.now()
        return currentTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
    }

}
