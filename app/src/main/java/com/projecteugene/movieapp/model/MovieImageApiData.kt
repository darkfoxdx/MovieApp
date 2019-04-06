package com.projecteugene.movieapp.model

/**
 * Created by Eugene Low
 */
data class MovieImageApiData(val id: Int,
                             val backdrops: List<MovieImageData>,
                             val posters: List<MovieImageData>) {
    val imagePathList: List<String>
        get() = posters.map { it.file_path }
}