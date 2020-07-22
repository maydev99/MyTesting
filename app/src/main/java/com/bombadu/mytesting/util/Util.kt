package com.bombadu.mytesting.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bombadu.mytesting.database.MyViewModel
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

open class RoutinesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}