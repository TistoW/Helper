package com.tisto.helpers.view

import android.content.res.Resources
import android.widget.NumberPicker
import java.time.LocalDateTime

val Int.convertToDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.convertToDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun NumberPicker.setDividerHeight(height: Int) {
    val pickerFields = NumberPicker::class.java.declaredFields
    for (pf in pickerFields) {
        if (pf.name == "mSelectionDividerHeight") {
            pf.isAccessible = true
            try {
                // set divider height in pixels
                pf.set(this, height)
            } catch (e: java.lang.IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            break
        }
    }
}

fun LocalDateTime.getFormatTime(): DateTimePicker.DateTimeFormat {
    return if (hour >= 12) {
        DateTimePicker.DateTimeFormat.PM
    } else {
        DateTimePicker.DateTimeFormat.AM
    }
}

fun DateTimePicker.DateTimeFormat.getFormatTimeValue(): Int {
    return if (this == DateTimePicker.DateTimeFormat.AM) {
        1
    } else {
        2
    }
}

fun LocalDateTime.switchFormatTime(): DateTimePicker.DateTimeFormat {
    return if (getFormatTime() == DateTimePicker.DateTimeFormat.PM) {
        DateTimePicker.DateTimeFormat.AM
    } else {
        DateTimePicker.DateTimeFormat.PM
    }
}

fun LocalDateTime.switchDateTime(format: DateTimePicker.DateTimeFormat): LocalDateTime {
    return if (format == DateTimePicker.DateTimeFormat.AM) {
        withHour(hour - 12)
    } else {
        withHour(hour + 12)
    }
}