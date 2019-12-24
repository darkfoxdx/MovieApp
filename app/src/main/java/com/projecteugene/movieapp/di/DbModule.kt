package com.projecteugene.movieapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.projecteugene.movieapp.api.ApiService
import com.projecteugene.movieapp.db.MovieDao
import com.projecteugene.movieapp.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

/**
 * Created by Eugene Low
 */
@Module
@Suppress("unused")
object DbModule {
    @Provides
    @JvmStatic
    fun provideMovieDao(application: Application): MovieDao {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movie_db").build().movieDao()
    }
}