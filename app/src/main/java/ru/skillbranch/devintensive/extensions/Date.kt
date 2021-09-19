package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 360 * DAY

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    //println(dateFormat)
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}
fun Date.humanizeDiff(date: Date = Date()): String {
    val date1 = this.time
    val date2 = date.time
    val dtd = date2 - date1
    val dd = when {
        dtd >= YEAR -> "более года назад"
        dtd >= DAY + (2 * HOUR) -> "${dtd / DAY} ${Utils.skl(dtd / DAY, 3)} назад"
        dtd >= DAY - (2 * HOUR) && dtd < DAY + (2 * HOUR) -> "день назад"
        dtd >= 75 * MINUTE && dtd < DAY - (2 * HOUR) -> "${dtd / HOUR} ${Utils.skl(dtd / HOUR, 2)} назад"
        dtd >= 45 * MINUTE && dtd < 75 * MINUTE -> "час назад"
        dtd >= 75 * SECOND && dtd < 45 * MINUTE -> "${dtd / MINUTE} ${Utils.skl(dtd / MINUTE, 1)} назад"
        dtd >= 45 * SECOND && dtd < 75 * SECOND -> "минуту назад"
        dtd >= 1 * SECOND && dtd < 45 * SECOND -> "несколько секунд назад"
        dtd < SECOND -> "только что"
        else -> ""
    }
    return dd
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {

        val ret = when(this) {
            TimeUnits.SECOND -> "$value ${Utils.sklNV(value.toLong(), 4)}"
            TimeUnits.MINUTE -> "$value ${Utils.sklNV(value.toLong(), 1)}"
            TimeUnits.HOUR -> "$value ${Utils.sklNV(value.toLong(), 2)}"
            TimeUnits.DAY -> "$value ${Utils.sklNV(value.toLong(), 3)}"
        }

        var h = this
        return ret
    }

}

