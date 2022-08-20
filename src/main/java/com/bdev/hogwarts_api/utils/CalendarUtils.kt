package com.bdev.hogwarts_api.utils

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.time.Month
import java.time.ZoneId
import java.util.*

object CalendarUtils {
    fun getInstance(): Calendar {
        return Calendar.getInstance().also { calendar ->
            calendar.timeZone = TimeZone.getTimeZone(ZoneId.of("Europe/Moscow"))

            calendar.firstDayOfWeek = Calendar.MONDAY
        }
    }

    fun getMonthStart(year: Int, month: Month): Long {
        val calendar = getInstance()

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month.calendarMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    fun getMonthFinish(year: Int, month: Month): Long {
        val calendar = getInstance()

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month.calendarMonth)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    fun getMonth(calendar: Calendar): Month {
        return Month.findByCalendarMonth(calendarMonth = calendar.get(Calendar.MONTH))!!
    }

    fun getDayOfWeek(calendar: Calendar): DayOfWeek {
        return DayOfWeek.findByCalendarDayOfWeek(calendarDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK))!!
    }

    fun getDayOfWeek(time: Long): DayOfWeek {
        val calendar = getInstance()

        calendar.timeInMillis = time

        return getDayOfWeek(calendar)
    }
}
