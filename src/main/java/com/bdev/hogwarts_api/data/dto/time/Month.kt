package com.bdev.hogwarts_api.data.dto.time

import java.util.*

enum class Month(
    val index: Int,
    val calendarMonth: Int
) {
    JAN(index =  0, calendarMonth = Calendar.JANUARY),
    FEB(index =  1, calendarMonth = Calendar.FEBRUARY),
    MAR(index =  2, calendarMonth = Calendar.MARCH),
    APR(index =  3, calendarMonth = Calendar.APRIL),
    MAY(index =  4, calendarMonth = Calendar.MAY),
    JUN(index =  5, calendarMonth = Calendar.JUNE),
    JUL(index =  6, calendarMonth = Calendar.JULY),
    AUG(index =  7, calendarMonth = Calendar.AUGUST),
    SEP(index =  8, calendarMonth = Calendar.SEPTEMBER),
    OCT(index =  9, calendarMonth = Calendar.OCTOBER),
    NOV(index = 10, calendarMonth = Calendar.NOVEMBER),
    DEC(index = 11, calendarMonth = Calendar.DECEMBER);

    companion object {
        fun findByIndex(index: Int): Month? {
            return values().find { month -> month.index == index }
        }

        fun findByCalendarMonth(calendarMonth: Int): Month? {
            return values().find { month -> month.index == calendarMonth }
        }
    }
}
