package com.bdev.hogwarts_api.data.dto

enum class EducationLevel(val id: String) {
    UNKNOWN("UNKNOWN"),
    BEGINNER("BEGINNER"),
    ELEMENTARY("ELEMENTARY"),
    PRE_INTERMEDIATE("PRE_INTERMEDIATE"),
    INTERMEDIATE("INTERMEDIATE"),
    UPPER_INTERMEDIATE("UPPER_INTERMEDIATE"),
    PRE_ADVANCED("PRE_ADVANCED"),
    ADVANCED("ADVANCED"),
    PROFICIENT("PROFICIENT");

    companion object {
        fun fromId(id: String): EducationLevel? {
            return values().find { it.id == id }
        }
    }
}
