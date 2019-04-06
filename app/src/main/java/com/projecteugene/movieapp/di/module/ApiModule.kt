package com.projecteugene.movieapp.di.module

import com.projecteugene.movieapp.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

/**
 * Created by Eugene Low
 */
@Module
@Suppress("unused")
object ApiModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitClient(): Retrofit {
        return ApiService.getInstance()
    }
}