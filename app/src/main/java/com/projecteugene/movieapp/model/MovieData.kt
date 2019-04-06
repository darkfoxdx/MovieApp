package com.projecteugene.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Eugene Low
 */
@Entity(tableName = "movies")
data class MovieData(val vote_count: Int,
                     @field:PrimaryKey
                     val id: Int,
                     val video: Boolean,
                     val vote_average: Double,
                     val title: String,
                     val popularity: Double,
                     val poster_path: String,
                     val original_language: String,
                     val original_title: String,
                     val genre_ids: IntArray,
                     val backdrop_path: String,
                     val adult: Boolean,
                     val overview: String,
                     val release_date: String)