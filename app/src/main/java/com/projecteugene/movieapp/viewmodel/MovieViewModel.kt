package com.projecteugene.movieapp.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.projecteugene.movieapp.activity.DetailActivity
import com.projecteugene.movieapp.utils.BaseViewModel
import com.projecteugene.movieapp.model.MovieData
import com.projecteugene.movieapp.utils.AppKey

/**
 * Created by Eugene Low
 */
class MovieViewModel: BaseViewModel() {
    val id = MutableLiveData<Int>()
    val title = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val image = MutableLiveData<String>()

    fun bind(movie: MovieData){
        id.value = movie.id
        title.value = movie.title
        date.value = movie.release_date
        image.value = movie.poster_path
    }

    fun onClickItem(view: View, item: MovieViewModel) {
        val intent = Intent(view.context, DetailActivity::class.java).apply {
            putExtra(AppKey.ID, item.id.value)
        }
        view.context.startActivity(intent)
    }
}