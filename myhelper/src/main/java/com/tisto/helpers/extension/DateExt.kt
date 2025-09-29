package com.tisto.helpers.extension

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

enum class DayBoundary {
    START, END, CURRENT
}

private fun Calendar.setBoundary(boundary: DayBoundary): Calendar {
    when (boundary) {
        DayBoundary.START -> {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        DayBoundary.END -> {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }

        DayBoundary.CURRENT -> {
            // do nothing
        }
    }
    return this
}


fun formatData(
    formatDate: String = defaultDateFormat,
    locale: Locale? = Locale.getDefault()
): SimpleDateFormat {
    return SimpleDateFormat(formatDate, locale)
}

@SuppressLint("SimpleDateFormat")
fun today(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance().setBoundary(boundary)
    return formatData(formatDate).format(calendar.time)
}

fun firstDayOfThisWeek(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun lastDayOfThisWeek(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek + 6) // Usually Sunday
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun firstDayOfLastWeek(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
    calendar.add(Calendar.WEEK_OF_YEAR, -1)
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun lastDayOfLastWeek(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun firstDayOfThisMonth(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun lastDayOfThisMonth(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun firstDayOfLastMonth(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MONTH, -1)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun lastDayOfLastMonth(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    calendar.add(Calendar.DATE, -1)
    return formatData(formatDate).format(calendar.setBoundary(boundary).time)
}

fun last30Day(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, -30) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}

fun next30Day(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, 30) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}

fun last7Day(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, -7) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}

fun next7Day(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, 7) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}


fun tomorrow(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, 1) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}

fun yesterday(
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, -1) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}


fun nextDay(
    day: Int = 1,
    formatDate: String = defaultDateFormat,
    boundary: DayBoundary = DayBoundary.CURRENT
): String {
    return Calendar.getInstance()
        .apply { add(Calendar.DAY_OF_MONTH, day) }
        .setBoundary(boundary)
        .let { formatData(formatDate).format(it.time) }
}


fun String.toCalender(formatDate: String = defaultDateFormat): Calendar {
    var format = formatDate
    if (this.contains("Z")) { // utc format
        format = defaultUTCDateFormat
    }
    val sdf = formatData(format)
    val date = sdf.parse(this)
    return date?.toCalendar() ?: Calendar.getInstance()
}

fun String.toDate(formatDate: String = defaultDateFormat): Date {
    return formatData(formatDate).parse(this) ?: Calendar.getInstance().toDate()
}

fun Date.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

fun Calendar.toDate(): Date {
    return this.time
}

fun Calendar.toLastMonth(): Date {
    add(Calendar.MONTH, -1)
    return time
}

fun Calendar.toLastDateOfMonth(): Date {
    add(Calendar.MONTH, 1)
    set(Calendar.DAY_OF_MONTH, 1)
    add(Calendar.DATE, -1)
    return time
}

fun Calendar.toNextWeek(): Date {
    add(Calendar.DAY_OF_WEEK, 6)
    return time
}

fun Calendar.toLastWeek(): Date {
    add(Calendar.DAY_OF_WEEK, -6)
    return time
}

fun Calendar.addDay(day: Int): Calendar {
    add(Calendar.DATE, day)
    return this
}

fun Calendar.getDate(format: String = defaultDateFormat): String {
    return formatData(format).format(time)
}

@SuppressLint("SimpleDateFormat")
fun currentTime(format: String = defaultDateFormat): String {
    val sdf = SimpleDateFormat(format)
    return sdf.format(Date())
}

@SuppressLint("SimpleDateFormat")
fun currentTimeUTC(isUTCTime: Boolean = true): String {
    val sdf = SimpleDateFormat(defaultDateFormatMillisecond)
    val result = sdf.format(Date())
    val addDay: Int? = if (isUTCTime) -7 else null
    return result.convertToUTC(defaultDateFormatMillisecond, addDay = addDay)
}

fun String?.toStartOfTheDay(): String {
    return this?.convertTanggal("yyyy-MM-dd") + " 00:00:00"
}

fun String?.toEndOfTheDay(): String {
    return this?.convertTanggal("yyyy-MM-dd") + " 23:59:59"
}

fun getStartOfTheDay(): String {
    return "${currentTime("yyyy-MM-dd")} 00:00:00"
}

fun getEndOfTheDay(): String {
    return "${currentTime("yyyy-MM-dd")} 23:59:59"
}

private fun datePickerDialog(
    title: String = "Select Date",
    date: String? = null, // formatDate yyyy-MM-dd,
    result: (String) -> Unit // // format result date yyyy-MM-dd,
): MaterialDatePicker<Long> {
    val builder = MaterialDatePicker.Builder
        .datePicker()
        .setTitleText(title)

    val selectedCalender = android.icu.util.Calendar.getInstance()
    if (!date.isNullOrEmpty()) {
        date.let { selectedDate ->
            val year = selectedDate.convertTanggal("yyyy").toIntSafety()
            val month = selectedDate.convertTanggal("MM").toIntSafety() - 1
            val day = selectedDate.convertTanggal("dd").toIntSafety()
            selectedCalender.set(year, month, day)
            builder.setSelection(selectedCalender.timeInMillis)
        }
    }
    val datePicker = builder.build()
    datePicker.addOnPositiveButtonClickListener { selection ->
        val calendar = android.icu.util.Calendar.getInstance()
        calendar.timeInMillis = selection
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = simpleDateFormat.format(calendar.time)
        result.invoke(selectedDate)
    }
    return datePicker
}

private fun timePickerDialog(
    title: String = "Select a Time",
    hour: Int = 0,
    minute: Int = 0,
    result: (hour: Int, minute: Int) -> Unit // // format result date kk:mm
): MaterialTimePicker {
    var currentHour = hour
    var currentMinute = minute
    if (hour == 0 && minute == 0) {
        val calendar = Calendar.getInstance()
        currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        currentMinute = calendar.get(Calendar.MINUTE)
    }

    val timeBuilder = MaterialTimePicker
        .Builder()
        .setHour(currentHour)
        .setMinute(currentMinute)
        .setTitleText(title)
        .setTimeFormat(TimeFormat.CLOCK_24H)
        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
    timeBuilder.setHour(hour)
    timeBuilder.setMinute(minute)
    val timePicker = timeBuilder.build()
    timePicker.addOnPositiveButtonClickListener {
        result.invoke(timePicker.hour, timePicker.minute)
    }
    return timePicker
}

fun dateTimePickerDialog(
    manager: FragmentManager,
    titleDate: String = "Select Date",
    titleTime: String = "Select a Time",
    dateTime: String? = null, // formatDate yyyy-MM-dd kk:mm:ss,
    result: (String) -> Unit // // format result date yyyy-MM-dd kk:mm:ss,
) {
    val builder = MaterialDatePicker.Builder
        .datePicker()
        .setTitleText(titleDate)

    val selectedCalender = android.icu.util.Calendar.getInstance()
    if (!dateTime.isNullOrEmpty()) {
        dateTime.let { selectedDate ->
            val year = selectedDate.convertTanggal("yyyy").toIntSafety()
            val month = selectedDate.convertTanggal("MM").toIntSafety() - 1
            val day = selectedDate.convertTanggal("dd").toIntSafety()
            selectedCalender.set(year, month, day)
            builder.setSelection(selectedCalender.timeInMillis)
        }
    }
    val datePicker = builder.build()
    datePicker.addOnPositiveButtonClickListener { selection ->
        val calendar = android.icu.util.Calendar.getInstance()
        calendar.timeInMillis = selection
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = simpleDateFormat.format(calendar.time)

        val timeBuilder = MaterialTimePicker
            .Builder()
            .setTitleText(titleTime)
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
        if (!dateTime.isNullOrEmpty()) {
            dateTime.let { selectedDate ->
                val hour = selectedDate.convertTanggal("kk").toIntSafety()
                val minute = selectedDate.convertTanggal("mm").toIntSafety()
                timeBuilder.setHour(hour)
                timeBuilder.setMinute(minute)
            }
        }
        val timePicker = timeBuilder.build()
        timePicker.show(manager, "TIME_PICKER")

        timePicker.addOnPositiveButtonClickListener {
            val time = "${timePicker.hour}:${timePicker.minute}"
            val selectedDate = "$date $time:00"
            result.invoke(selectedDate)
        }
    }
    datePicker.show(manager, "DatePicker")
}

fun Fragment.datePicker(
    title: String = "Select Date",
    date: String? = null, // formatDate yyyy-MM-dd,
    result: (String) -> Unit // // format result date yyyy-MM-dd,
) {
    datePickerDialog(title, date, result).show(childFragmentManager, "DatePicker")
}

fun AppCompatActivity.datePicker(
    title: String = "Select Date",
    date: String? = null, // formatDate yyyy-MM-dd,
    result: (String) -> Unit // // format result date yyyy-MM-dd,
) {
    datePickerDialog(title, date, result).show(supportFragmentManager, "DatePicker")
}

fun Fragment.timePicker(
    title: String = "Select a Time",
    hour: Int = 0,
    minute: Int = 0,
    result: (hour: Int, minute: Int) -> Unit // // format result date kk:mm
) {
    timePickerDialog(title, hour, minute, result).show(childFragmentManager, "timePicker")
}

fun AppCompatActivity.timePicker(
    title: String = "Select a Time",
    hour: Int = 0,
    minute: Int = 0,
    result: (hour: Int, minute: Int) -> Unit // // format result date kk:mm
) {
    timePickerDialog(title, hour, minute, result).show(supportFragmentManager, "timePicker")
}

fun Fragment.dateTimePicker(
    titleDate: String = "Select Date",
    titleTime: String = "Select a Time",
    dateTime: String? = null, // formatDate yyyy-MM-dd kk:mm:ss,
    result: (String) -> Unit // // format result date yyyy-MM-dd kk:mm:ss,
) {
    dateTimePickerDialog(childFragmentManager, titleDate, titleTime, dateTime, result)
}

fun AppCompatActivity.dateTimePicker(
    titleDate: String = "Select Date",
    titleTime: String = "Select a Time",
    dateTime: String? = null, // formatDate yyyy-MM-dd kk:mm:ss,
    result: (String) -> Unit // // format result date yyyy-MM-dd kk:mm:ss,
) {
    dateTimePickerDialog(supportFragmentManager, titleDate, titleTime, dateTime, result)
}
