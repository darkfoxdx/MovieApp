package com.projecteugene.movieapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.projecteugene.movieapp.db.MovieDatabase

/**
 * Created by Eugene Low
 */
class LatestMovieApiViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LatestMovieApiViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, MovieDatabase::class.java, "movie_db").build()
            @Suppress("UNCHECKED_CAST")
            return LatestMovieApiViewModel(db.movieDao()) as T
        }
        throw IllegalArgumentException("Invalid class")

    }
}