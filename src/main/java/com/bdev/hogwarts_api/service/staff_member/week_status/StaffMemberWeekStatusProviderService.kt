package com.bdev.hogwarts_api.service.staff_member.week_status

import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusType
import com.bdev.hogwarts_api.data.dto.time.Month
import com.bdev.hogwarts_api.data.dto.time.MonthAndYear
import com.bdev.hogwarts_api.data.dto.time.Week
import com.bdev.hogwarts_api.utils.CalendarUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.min

interface StaffMemberWeekStatusProviderService {
    fun getStaffMemberWeekStatuses(
        staffMemberLogin: String,
        calculationTime: Long
    ): Map<MonthAndYear, Map<Week, StaffMemberWeekStatusType>>
}

@Service
class StaffMemberWeekStatusProviderServiceImpl @Autowired constructor(
    private val staffMemberWeekStatusStorageService: StaffMemberWeekStatusStorageService
): StaffMemberWeekStatusProviderService {
    override fun getStaffMemberWeekStatuses(
        staffMemberLogin: String,
        calculationTime: Long
    ): Map<MonthAndYear, Map<Week, StaffMemberWeekStatusType>> {
        val result: MutableMap<MonthAndYear, Map<Week, StaffMemberWeekStatusType>> = HashMap()

        val start = MonthAndYear(month = Month.SEP, year = 2021)
        val finish = MonthAndYear(month = Month.MAY, year = 2022)

        val allWeekStatuses = staffMemberWeekStatusStorageService.getAllForStaffMember(
            staffMemberLogin = staffMemberLogin
        )

        getMonths(start = start, finish = finish).forEach { monthAndYear ->
            val resultMonth: MutableMap<Week, StaffMemberWeekStatusType> = HashMap()

            getWeeks(monthAndYear = monthAndYear).forEach { week ->
                val weekStatus = getWeekStatus(
                    week = week,
                    monthAndYear = monthAndYear,
                    allWeekStatuses = allWeekStatuses,
                    calculationTime = calculationTime
                )

                resultMonth[week] = weekStatus
            }

            result[monthAndYear] = resultMonth
        }

        return result
    }

    private fun getWeekStatus(
        week: Week,
        monthAndYear: MonthAndYear,
        allWeekStatuses: List<StaffMemberWeekStatus>,
        calculationTime: Long
    ): StaffMemberWeekStatusType {
        return if (calculationTime < week.finishTime) {
            StaffMemberWeekStatusType.FUTURE
        } else {
            val weekStatus = allWeekStatuses.find { weekStatus ->
                val monthMatches = weekStatus.id.month == monthAndYear.month
                val yearMatches = weekStatus.id.year == monthAndYear.year

                monthMatches && yearMatches
            }

            weekStatus?.type ?: StaffMemberWeekStatusType.OPENED
        }
    }

    private fun getMonths(start: MonthAndYear, finish: MonthAndYear): List<MonthAndYear> {
        val result = ArrayList<MonthAndYear>()

        var current = start

        do {
            result.add(current)

            val nextMonth = if (current.month == Month.DEC) {
                Month.JAN
            } else {
                Month.findByIndex(current.month.index + 1) ?: Month.JAN
            }

            val nextYear = if (current.month == Month.DEC) {
                current.year + 1
            } else {
                current.year
            }

            current = MonthAndYear(month = nextMonth, year = nextYear)
        } while (current != finish)

        return result
    }

    private fun getWeeks(monthAndYear: MonthAndYear): List<Week> {
        val res = ArrayList<Week>()

        val dayInMills = 24 * 60 * 60 * 1000L

        val monthStartTime = CalendarUtils.getMonthStart(
            year = monthAndYear.year,
            month = monthAndYear.month
        )

        val monthFinishTime = CalendarUtils.getMonthFinish(
            year = monthAndYear.year,
            month = monthAndYear.month
        )

        var currentTime = monthStartTime
        var currentWeekIndex = 0

        while (currentTime < monthFinishTime) {
            val daysInWeek = 7 - CalendarUtils.getDayOfWeek(time = currentTime).index

            val startTime = currentTime
            val finishTime = startTime + daysInWeek * dayInMills

            res.add(
                Week(
                    index = currentWeekIndex,
                    startTime = startTime,
                    finishTime = min(monthFinishTime, finishTime)
                )
            )

            currentWeekIndex++

            currentTime = finishTime
        }

        return res
    }
}
