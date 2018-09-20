package com.bdev.hogwarts_api.data.dto.student;

public enum StudentStatusType {
    REQUEST,
    REQUEST_LEFT,

    TEST,
    TEST_STOPPED,
    TEST_LEFT,

    FREE_LESSON,
    FREE_LESSON_STOPPED,
    FREE_LESSON_LEFT,

    AWAITING_GROUP,
    AWAITING_GROUP_STOPPED,
    AWAITING_GROUP_LEFT,

    STUDYING,
    STUDYING_STOPPED,
    STUDYING_LEFT
}
