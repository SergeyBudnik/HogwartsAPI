package com.bdev.hogwarts_api.data.dto.common

import java.util.*

enum class DayOfWeek(
    val index: Int,
    val calendarDayOfWeek: Int
) {
    MONDAY(index = 0, calendarDayOfWeek = Calendar.MONDAY),
    TUESDAY(index = 1, calendarDayOfWeek = Calendar.TUESDAY),
    WEDNESDAY(index = 2, calendarDayOfWeek = Calendar.WEDNESDAY),
    THURSDAY(index = 3, calendarDayOfWeek = Calendar.THURSDAY),
    FRIDAY(index = 4, calendarDayOfWeek = Calendar.FRIDAY),
    SATURDAY(index = 5, calendarDayOfWeek = Calendar.SATURDAY),
    SUNDAY(index = 6, calendarDayOfWeek = Calendar.SUNDAY);

    companion object {
        fun findByCalendarDayOfWeek(calendarDayOfWeek: Int): DayOfWeek? {
            return values().find { dayOfWeek ->
                dayOfWeek.calendarDayOfWeek == calendarDayOfWeek
            }
        }
    }
}
