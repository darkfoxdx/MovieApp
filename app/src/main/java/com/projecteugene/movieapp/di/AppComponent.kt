package com.projecteugene.movieapp.di

import android.app.Application
import com.projecteugene.movieapp.MovieApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    ApiModule::class,
    ViewModelFactoryModule::class,
    ViewModelBindingModule::class,
    DbModule::class])
interface AppComponent : AndroidInjector<MovieApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}