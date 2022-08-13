package com.bdev.hogwarts_api.data.dto.common

enum class LessonTime(val index: Int, val hour: Int, val minutes: Int) {
    T_07_00(index =  0, hour =  7, minutes =  0),
    T_07_30(index =  1, hour =  7, minutes = 30),
    T_08_00(index =  2, hour =  8, minutes =  0),
    T_08_30(index =  3, hour =  8, minutes = 30),
    T_09_00(index =  4, hour =  9, minutes =  0),
    T_09_30(index =  5, hour =  9, minutes = 30),
    T_10_00(index =  6, hour = 10, minutes =  0),
    T_10_30(index =  7, hour = 10, minutes = 30),
    T_11_00(index =  8, hour = 11, minutes =  0),
    T_11_30(index =  9, hour = 11, minutes = 30),
    T_12_00(index = 10, hour = 12, minutes =  0),
    T_12_30(index = 11, hour = 12, minutes = 30),
    T_13_00(index = 12, hour = 13, minutes =  0),
    T_13_30(index = 13, hour = 13, minutes = 30),
    T_14_00(index = 14, hour = 14, minutes =  0),
    T_14_30(index = 15, hour = 14, minutes = 30),
    T_15_00(index = 16, hour = 15, minutes =  0),
    T_15_30(index = 17, hour = 15, minutes = 30),
    T_16_00(index = 18, hour = 16, minutes =  0),
    T_16_30(index = 19, hour = 16, minutes = 30),
    T_17_00(index = 20, hour = 17, minutes =  0),
    T_17_30(index = 21, hour = 17, minutes = 30),
    T_18_00(index = 22, hour = 18, minutes =  0),
    T_18_30(index = 23, hour = 18, minutes = 30),
    T_19_00(index = 24, hour = 19, minutes =  0),
    T_19_30(index = 25, hour = 19, minutes = 30),
    T_20_00(index = 26, hour = 20, minutes =  0),
    T_20_30(index = 27, hour = 20, minutes = 30),
    T_21_00(index = 28, hour = 21, minutes =  0),
    T_21_30(index = 29, hour = 21, minutes = 30),
    T_22_00(index = 30, hour = 22, minutes =  0),
    T_22_30(index = 31, hour = 22, minutes = 30),
    T_23_00(index = 32, hour = 23, minutes =  0),
    T_23_30(index = 33, hour = 23, minutes = 30);

    companion object {
        fun fromIndex(index: Int): LessonTime? {
            return values().find { lessonTime -> lessonTime.index == index }
        }

        fun fromMinIndex(): LessonTime {
            return values().minBy { lessonTime -> lessonTime.index } ?: T_07_00
        }
    }
}
