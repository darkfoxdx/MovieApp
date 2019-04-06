package com.projecteugene.movieapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.projecteugene.movieapp.db.MovieDatabase

/**
 * Created by Eugene Low
 */
class MovieImageApiViewModelFactory(private val activity: AppCompatActivity,
                                    private val id: Int,
                                    private val fm: FragmentManager): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieImageApiViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, MovieDatabase::class.java, "movie_db").build()
            @Suppress("UNCHECKED_CAST")
            return MovieImageApiViewModel(db.movieDao(), id, fm) as T
        }
        throw IllegalArgumentException("Invalid class")

    }
}