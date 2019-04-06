package com.projecteugene.movieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.projecteugene.movieapp.model.MovieInfoApiData
import com.projecteugene.movieapp.model.MovieData

/**
 * Created by Eugene Low
 */
@Database(entities = [MovieData::class, MovieInfoApiData::class], version = 1, exportSchema = false)
@TypeConverters(MovieDataTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}