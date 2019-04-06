package com.projecteugene.movieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projecteugene.movieapp.model.MovieInfoApiData
import com.projecteugene.movieapp.model.MovieData

/**
 * Created by Eugene Low
 */
@Dao
interface MovieDao {
    @get:Query("SELECT * FROM movies")
    val all: List<MovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(movieInfo: MovieInfoApiData)

    @Query("SELECT * FROM movie_info WHERE id = :id LIMIT 1")
    fun getInfo(id: Int): MovieInfoApiData?
}