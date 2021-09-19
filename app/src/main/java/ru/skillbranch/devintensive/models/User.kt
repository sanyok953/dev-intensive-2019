package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import java.util.Collections.unmodifiableList



data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    var isOnline: Boolean = false

) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")


    private constructor(builder: Builder): this(builder.id, builder.firstName, builder.lastName, builder.avatar, builder.rating, builder.respect, builder.lastVisit, builder.isOnline)

        //id = builder.id()
//        val builtEmployees = ArrayList()
//        for (employee in builder.employees) {
//            builtEmployees.add(employee.build())
//        }
//        val builtOffices = ArrayList()
//        for (office in builder.offices) {
//            builtOffices.add(office.build())
//        }
//        employees = Collections.unmodifiableList(builtEmployees)
//        offices = Collections.unmodifiableList(builtOffices)
//        name = builder.name
//        marketCap = builder.marketCap
//        annualCosts = builder.annualCosts
//        annualRevenue = builder.annualRevenue


    init {
        println("It`` Alive!!!\n" +
            "${if (lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n"
        )
    }


    class Builder {
        var id: String = ""
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = null
        var isOnline: Boolean = false

        fun build(): User {
            return User(this)
        }

        fun id(s: String): Builder {
            id = s
            return this
        }
        fun firstName(s: String?): Builder {
            firstName = s
            return this
        }
        fun lastName(s: String?): Builder {
            lastName = s
            return this
        }
        fun avatar(s: String?): Builder {
            avatar = s
            return this
        }
        fun rating(n: Int): Builder {
            rating = n
            return this
        }
        fun respect(n: Int): Builder {
            respect = n
            return this
        }
        fun lastVisit(d: Date?): Builder {
            lastVisit = d
            return this
        }
        fun isOnline(b: Boolean): Builder {
            isOnline = b
            return this
        }
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId ++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

}