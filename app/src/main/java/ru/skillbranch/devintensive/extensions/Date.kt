package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
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
    var dtd = 0L;
    if (date1 < date2)
        dtd = date2 - date1
    else
        dtd = date1 - date2

    val dd = if(date1 < date2) when {
        dtd >= (360 * DAY) -> "более года назад"
        dtd >= DAY + (2 * HOUR) && dtd < DAY * 360 -> "${dtd / DAY} ${Utils.skl(dtd / DAY,3)} назад"
        dtd >= DAY - (2 * HOUR) && dtd < DAY + (2 * HOUR) -> "день назад"
        dtd >= 75 * MINUTE && dtd < DAY - (2 * HOUR) -> "${dtd / HOUR} ${Utils.skl(dtd / HOUR,2)} назад"
        dtd >= 45 * MINUTE && dtd < 75 * MINUTE -> "час назад"
        dtd >= 75 * SECOND && dtd < 45 * MINUTE -> "${dtd / MINUTE} ${Utils.skl(dtd / MINUTE,1)} назад"
        dtd >= 45 * SECOND && dtd < 75 * SECOND -> "минуту назад"
        dtd >= 1 * SECOND && dtd < 45 * SECOND -> "несколько секунд назад"
        dtd < SECOND -> "только что"
        //dtd > SECOND -> "только что"
        //dtd <= (360 * DAY) -> "более чем через год"
        //dtd <= DAY + (2 * HOUR) && dtd > DAY * 360 -> "через ${dtd / DAY} ${Utils.skl(dtd / DAY,3)}"
        else -> ""
    } else { when {
        dtd >= (360 * DAY) -> "более чем через год"
        dtd >= DAY + (2 * HOUR) && dtd < DAY * 360 -> "через ${dtd / DAY} ${Utils.skl(dtd / DAY,3)}"
        dtd >= DAY - (2 * HOUR) && dtd < DAY + (2 * HOUR) -> "через день"
        dtd >= 75 * MINUTE && dtd < DAY - (2 * HOUR) -> "через ${dtd / HOUR} ${Utils.skl(dtd / HOUR,2)}"
        dtd >= 45 * MINUTE && dtd < 75 * MINUTE -> "через час"
        dtd >= 75 * SECOND && dtd < 45 * MINUTE -> "через ${dtd / MINUTE} ${Utils.skl(dtd / MINUTE,1)}"
        dtd >= 45 * SECOND && dtd < 75 * SECOND -> "через минуту"
        dtd >= 1 * SECOND && dtd < 45 * SECOND -> "через несколько секунд"
        dtd < SECOND -> "только что"
        else -> ""
    }
    }
    return dd
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return value.toString() + " " +
            when {
                this == SECOND -> when {
                    value != 11 && value % 10 == 1 -> "секунду"
                    value > 4 && (value in 5..20 || value % 10 in 5..9 || value % 10 == 0) -> "секунд"
                    else -> "секунды"
                }

                this == MINUTE -> when {
                    value != 11 && value % 10 == 1 -> "минуту"
                    value > 4 && (value in 5..20 || value % 10 in 5..9 || value % 10 == 0) -> "минут"
                    else -> "минуты"
                }

                this == HOUR -> when {
                    value != 11 && value % 10 == 1 -> "час"
                    value > 4 && (value in 5..20 || value % 10 in 5..9 || value % 10 == 0) -> "часов"
                    else -> "часа"
                }

                this == DAY -> when {
                    value != 11 && value % 10 == 1 -> "день"
                    value > 4 && (value in 5..20 || value % 10 in 5..9 || value % 10 == 0) -> "дней"
                    else -> "дня"
                }

                else -> ""
            }
    }
}