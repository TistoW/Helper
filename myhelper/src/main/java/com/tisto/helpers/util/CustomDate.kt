package com.tisto.helpers.util

import com.tisto.helpers.extension.*

object CustomDate {
    fun getToday(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = today(formatDate)

    fun getFirstDayOfThisWeek(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = firstDayOfThisWeek(formatDate, boundary)

    fun getLastDayOfThisWeek(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = lastDayOfThisWeek(formatDate, boundary)

    fun getFirstDayOfLastWeek(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = firstDayOfLastWeek(formatDate, boundary)

    fun getLastDayOfLastWeek(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = lastDayOfLastWeek(formatDate, boundary)

    fun getFirstDayOfThisMonth(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = firstDayOfThisMonth(formatDate, boundary)

    fun getLastDayOfThisMonth(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = lastDayOfThisMonth(formatDate, boundary)

    fun getFirstDayOfLastMonth(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = firstDayOfLastMonth(formatDate, boundary)

    fun getLastDayOfLastMonth(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = lastDayOfLastMonth(formatDate, boundary)

    fun getLast30Day(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = last30Day(formatDate, boundary)

    fun getNext30Day(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = next30Day(formatDate, boundary)

    fun getLast7Day(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = last7Day(formatDate, boundary)

    fun getNext7Day(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = next7Day(formatDate, boundary)

    fun getTomorrow(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = tomorrow(formatDate, boundary)

    fun getYesterday(
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = yesterday(formatDate, boundary)

    fun getNextDay(
        day: Int = 1,
        formatDate: String = defaultDateFormat,
        boundary: DayBoundary = DayBoundary.CURRENT
    ) = nextDay(day, formatDate, boundary)
}