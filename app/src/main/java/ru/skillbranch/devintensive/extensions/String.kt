package ru.skillbranch.devintensive.extensions


fun String.truncate(v: Int = 16): String {
    var str = ""
    val to = this.trimEnd()
    var more = "..."
    if (to.length > v) {
        var i = 1
        for (c in to) {
            if (i == v) {
                if (c.toString() != " ")
                    str += c
                break
            }
            str += c
            i++
        }
    } else {
        str = to
        more = ""
    }

    return "$str$more"
}