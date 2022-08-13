package com.bdev.hogwarts_api.utils

import java.time.ZoneId
import java.util.*

object CalendarUtils {
    fun getInstance(): Calendar {
        return Calendar.getInstance().also { calendar ->
            calendar.timeZone = TimeZone.getTimeZone(ZoneId.of("Europe/Moscow"))

            calendar.firstDayOfWeek = Calendar.MONDAY
        }
    }
}
