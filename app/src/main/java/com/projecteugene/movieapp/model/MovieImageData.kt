package com.projecteugene.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Eugene Low
 */
data class MovieImageData(val aspect_ratio: Double,
                          val file_path: String,
                          val height: Int,
                          val iso_639_1: String,
                          val vote_average: Double,
                          val vote_count: Int,
                          val width: Int)