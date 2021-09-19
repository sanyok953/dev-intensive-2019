package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    var mistakes = 0
    var restart = false

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        restart = false

        if (!checkAnswer(answer)) {
            val w = when (question) {
                Question.NAME -> "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
                Question.PROFESSION -> "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
                Question.MATERIAL -> "Материал не должен содержать цифр\n${question.question}" to status.color
                Question.BDAY -> "Год моего рождения должен содержать только цифры\n${question.question}" to status.color
                Question.SERIAL -> "Серийный номер содержит только цифры, и их 7\n${question.question}" to status.color
                Question.IDLE -> "break\n${question.question}" to status.color
            }
            if (w.first != "break") {
                return w
            }
        }

        val an = answer.toLowerCase()

        if (question == Question.IDLE) {
            status = Status.NORMAL
            return "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color
        }

        return if (question.answers.contains(an)) {
            question = question.nextQuestion()

            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            mistakes++
            if (mistakes < 3) {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            } else {
                this.status = Status.NORMAL
                this.question = Question.NAME
                mistakes = 0
                restart = true
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
        }
    }

    private fun checkAnswer(answer: String): Boolean {
        var ch = 'a'
        var ret = false
        if (question == Question.NAME) {
            for (c: Char in answer) {
                ch = c
                break
            }
            ret = ch.isUpperCase()

        } else if (question == Question.PROFESSION) {
            for (c: Char in answer) {
                ch = c
                break
            }
            ret = ch.isLowerCase()

        } else if (question == Question.MATERIAL) {
            for (c: Char in answer) {
                if (c.isDigit()) {
                    ret = false
                    break
                }
                ret = true
            }

        } else if (question == Question.BDAY) {
            for (c: Char in answer) {
                if (!c.isDigit()) {
                    ret = false
                    break
                } else {
                    ret = true
                }
            }


        } else if (question == Question.SERIAL) {
            if (answer.length == 7) {
                for (c: Char in answer) {
                    if (!c.isDigit()) {
                        ret = false
                        break
                    } else {
                        ret = true
                    }
                }
            } else {
                ret = false
            }

        } else if (question == Question.IDLE) {
            ret = true
        }

        return ret
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }

}