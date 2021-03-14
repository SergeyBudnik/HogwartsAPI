package com.bdev.hogwarts_api.data.dto

enum class Age(val id: String) {
    UNKNOWN("UNKNOWN"),
    PRE_SCHOOL("PRE_SCHOOL"),
    SCHOOL_1_3("SCHOOL_1_3"),
    SCHOOL_5_7("SCHOOL_5_7"),
    SCHOOL_8_9("SCHOOL_8_9"),
    SCHOOL_10_11("SCHOOL_10_11"),
    ADULT("ADULT"),
    SENIOR("SENIOR");

    companion object {
        fun fromId(id: String): Age? {
            return values().find { it.id == id }
        }
    }
}
