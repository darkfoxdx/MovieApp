package com.projecteugene.movieapp.utils

import androidx.lifecycle.ViewModel
import com.projecteugene.movieapp.di.component.DaggerViewModelInjector
import com.projecteugene.movieapp.di.component.ViewModelInjector
import com.projecteugene.movieapp.di.module.ApiModule
import com.projecteugene.movieapp.viewmodel.LatestMovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieImageApiViewModel

/**
 * Created by Eugene Low
 */

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .apiModule(ApiModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LatestMovieApiViewModel -> injector.inject(this)
            is MovieApiViewModel -> injector.inject(this)
            is MovieImageApiViewModel -> injector.inject(this)
        }
    }
}