package com.projecteugene.movieapp.di

import com.projecteugene.movieapp.activity.DetailActivity
import com.projecteugene.movieapp.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Eugene Low
 */
@Module
abstract class ActivityBindingModule  {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun detailActivity(): DetailActivity
}