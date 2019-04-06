package com.projecteugene.movieapp.api

import com.projecteugene.movieapp.model.LatestMovieApiData
import com.projecteugene.movieapp.model.MovieInfoApiData
import com.projecteugene.movieapp.model.MovieImageApiData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Eugene Low
 */
interface ApiService {

    @GET("movie/now_playing?api_key=$API_KEY")
    fun getLatest(): Observable<LatestMovieApiData>

    @GET("movie/{id}/images?api_key=$API_KEY&include_image_language=en,null")
    fun getMovieImage(@Path("id") movieId: Int): Observable<MovieImageApiData>

    @GET("movie/{id}?api_key=$API_KEY")
    fun getMovie(@Path("id") movieId: Int): Observable<MovieInfoApiData>

    companion object Client {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "51cb894a286928fa8d85bea58e45ea30"
        fun getInstance(): Retrofit {
            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}