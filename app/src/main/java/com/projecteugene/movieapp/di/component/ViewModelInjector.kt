package com.projecteugene.movieapp.di.component

import com.projecteugene.movieapp.di.module.ApiModule
import com.projecteugene.movieapp.utils.BaseViewModel
import com.projecteugene.movieapp.viewmodel.LatestMovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieImageApiViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Eugene Low
 */
@Singleton
@Component(modules = [(ApiModule::class)])
interface ViewModelInjector {
    fun inject(viewModel: LatestMovieApiViewModel)
    fun inject(viewModel: MovieApiViewModel)
    fun inject(viewModel: MovieImageApiViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun apiModule(apiModule: ApiModule): Builder
    }
}