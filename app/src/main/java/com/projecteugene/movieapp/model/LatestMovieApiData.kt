package com.projecteugene.movieapp.model

/**
 * Created by Eugene Low
 */
data class LatestMovieApiData(val page: Int,
                              val total_results: Int,
                              val total_pages: Int,
                              val results: List<MovieData>)