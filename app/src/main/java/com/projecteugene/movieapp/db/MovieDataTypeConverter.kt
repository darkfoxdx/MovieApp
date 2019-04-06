package com.projecteugene.movieapp.db

import androidx.room.TypeConverter


/**
 * Created by Eugene Low
 */
class MovieDataTypeConverter {

    @TypeConverter
    fun fromString(value: String?): IntArray? {
        return value?.split(",")?.map { it.toInt() }?.toIntArray()
    }

    @TypeConverter
    fun fromIntArray(array: IntArray): String {
        return array.joinToString(",")
    }
}