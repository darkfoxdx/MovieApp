package com.projecteugene.movieapp.di

import androidx.lifecycle.ViewModel
import com.projecteugene.movieapp.viewmodel.LatestMovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieImageApiViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Eugene Low
 */
@Module
abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(LatestMovieApiViewModel::class)
    abstract fun bindLatestMovieApiViewModel(viewModel: LatestMovieApiViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieApiViewModel::class)
    abstract fun bindMovieApiViewModel(viewModel: MovieApiViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieImageApiViewModel::class)
    abstract fun bindMovieImageApiViewModel(viewModel: MovieImageApiViewModel): ViewModel
}