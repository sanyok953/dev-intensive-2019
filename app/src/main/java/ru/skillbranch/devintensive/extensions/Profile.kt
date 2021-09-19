package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.utils.Utils

fun Profile.nickName(): String {
    val f = this.firstName
    val l = this.lastName
    return "${Utils.transliteration(f)}_${Utils.transliteration(l)}"
}