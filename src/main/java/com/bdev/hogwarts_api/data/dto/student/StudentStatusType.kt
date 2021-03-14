package com.bdev.hogwarts_api.data.dto.student

enum class StudentStatusType(val id: String) {
    STUDYING("STUDYING"),
    STOPPED("STOPPED"),
    LEFT("LEFT");

    companion object {
        fun fromId(id: String): StudentStatusType? {
            return values().find { it.id == id }
        }
    }
}
