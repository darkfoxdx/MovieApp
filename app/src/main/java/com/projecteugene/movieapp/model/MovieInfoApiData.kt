package com.projecteugene.movieapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Eugene Low
 */

@Entity(tableName="movie_info",
        foreignKeys = [ForeignKey(
            entity = MovieData::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.NO_ACTION
        )]
)
data class MovieInfoApiData(val adult: Boolean,
                            val backdrop_path: String?,
                            val budget: Long,
                            val homepage: String?,
                            @PrimaryKey
                            val id: Int,
                            val imdb_id: String?,
                            val original_language: String,
                            val original_title: String,
                            val overview: String?,
                            val popularity: Double,
                            val poster_path: String?,
                            val release_date: String,
                            val revenue: Long,
                            val runtime: Int?,
                            val status: String,
                            val tagline: String?,
                            val title: String,
                            val video: Boolean,
                            val vote_average: Double,
                            val vote_count: Int)