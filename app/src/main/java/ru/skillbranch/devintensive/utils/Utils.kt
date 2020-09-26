package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {

    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        if(fullName == "" || fullName == " ") return null to null
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun getLetter(le: String): String {
        return when(le) {
            "а"-> "a"
            "б"-> "b"
            "в"-> "v"
            "г"-> "g"
            "д"-> "d"
            "е"-> "e"
            "ё"-> "e"
            "ж"-> "zh"
            "з"-> "z"
            "и"-> "i"
            "й"-> "i"
            "к"-> "k"
            "л"-> "l"
            "м"-> "m"
            "н"-> "n"
            "о"-> "o"
            "п"-> "p"
            "р"-> "r"
            "с"-> "s"
            "т"-> "t"
            "у"-> "u"
            "ф"-> "f"
            "х"-> "h"
            "ц"-> "c"
            "ч"-> "ch"
            "ш"-> "sh"
            "щ"-> "sh'"
            "ъ"-> ""
            "ы"-> "i"
            "ь"-> ""
            "э"-> "e"
            "ю"-> "yu"
            "я"-> "ya"
            "А"-> "A"
            "Б"-> "B"
            "В"-> "V"
            "Г"-> "G"
            "Д"-> "D"
            "Е"-> "E"
            "Ё"-> "E"
            "Ж"-> "Zh"
            "З"-> "Z"
            "И"-> "I"
            "Й"-> "I"
            "К"-> "K"
            "Л"-> "L"
            "М"-> "M"
            "Н"-> "N"
            "О"-> "O"
            "П"-> "P"
            "Р"-> "R"
            "С"-> "S"
            "Т"-> "T"
            "У"-> "U"
            "Ф"-> "F"
            "Х"-> "H"
            "Ц"-> "W"
            "Ч"-> "Ch"
            "Ш"-> "Sh"
            "Щ"-> "Sh'"
            "Ъ"-> ""
            "Ы"-> "I"
            "Ь"-> ""
            "Э"-> "E"
            "Ю"-> "Yu"
            "Я"-> "Ya"
            else -> le
        }
    }

    fun skl(digit: Long, type: Int): String {
        val array1 : Array<String> = arrayOf("минуту", "минуты", "минут")
        val array2 : Array<String> = arrayOf("час", "часа", "часов")
        val array3 : Array<String> = arrayOf("день", "дня", "дней")
        val array4 : Array<String> = arrayOf("секунда", "секунды", "секунд")

        val array: Array<String> = if (type == 1) array1 else if (type == 2) array2 else if (type == 3) array3 else array4
        return when(sklCheck(digit)) {
            1L -> array[0]
            2L, 3L, 4L -> array[1]
            else -> array[2]
        }

    }

    fun sklCheck(value: Long): Long {
        val m = value % 10
        if (m == 1L || m == 2L || m == 3L || m == 4L) {
            if (value > 10) {
                var f = ""
                var l = ""
                for (i in value.toString()) {
                    f = l
                    l = i.toString()
                }
                if ((f+l).contains("11") || ("$f$l").contains("12") || ("$f$l").contains("13") || ("$f$l").contains("14")) {
                    return 8L
                }
            }
            return m
        } else {
            return m
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val name = payload.split(" ")
        var n1 = ""
        var n2 = ""
        for(i in name[0])
            n1 += getLetter(i.toString())
        for(i in name[1] )
            n2 += getLetter(i.toString())
        return "$n1$divider$n2"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var name: String? = ""
        if(firstName == null && lastName == null)
            name = null
        else if (firstName != null && lastName == null && !firstName.isBlank())
            name = firstName[0].toString().toUpperCase()
        else if (firstName == null && lastName != null && !lastName.isBlank())
            name = lastName[0].toString().toUpperCase()
        else if (firstName != null && lastName != null) {
            if (firstName.isBlank() && lastName.isBlank())
                name = null;
            else
                name = (firstName[0].toString() + lastName[0].toString()).toUpperCase()
        }
        return name
    }
}