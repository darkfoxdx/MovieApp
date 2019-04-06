package com.projecteugene.movieapp.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.projecteugene.movieapp.adapter.ImageFragmentAdapter
import com.projecteugene.movieapp.adapter.MovieAdapter
import com.projecteugene.movieapp.utils.BaseViewModel
import com.projecteugene.movieapp.api.ApiService
import com.projecteugene.movieapp.db.MovieDao
import com.projecteugene.movieapp.model.MovieImageApiData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eugene Low
 */
class MovieImageApiViewModel(private val movieDao: MovieDao,
                             private val id: Int,
                             private val fm: FragmentManager): BaseViewModel() {
    @Inject
    lateinit var apiService: ApiService

    private lateinit var disposable: Disposable
    val isLoading:MutableLiveData<Boolean> = MutableLiveData()
    val adapter = ImageFragmentAdapter(fm)

    init{
        call()
    }

    private fun call(){
        disposable  = apiService.getMovieImage(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .subscribe(
                { onSuccess(it) },
                { onError(it) }
            )
    }

    private fun onStart() {
        isLoading.value = true
    }

    private fun onSuccess(value: MovieImageApiData) {
        Log.d("movieimages onSuccess", "$value")
        isLoading.value = false
        adapter.update(value.imagePathList)
    }

    private fun onError(throwable: Throwable) {
        Log.e("movieimages onError", "error", throwable)
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}