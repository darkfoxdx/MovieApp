package com.projecteugene.movieapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.projecteugene.movieapp.db.MovieDatabase

/**
 * Created by Eugene Low
 */
class MovieApiViewModelFactory(private val activity: AppCompatActivity,
                               private val id: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieApiViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, MovieDatabase::class.java, "movie_db").build()
            @Suppress("UNCHECKED_CAST")
            return MovieApiViewModel(db.movieDao(), id) as T
        }
        throw IllegalArgumentException("Invalid class")

    }
}