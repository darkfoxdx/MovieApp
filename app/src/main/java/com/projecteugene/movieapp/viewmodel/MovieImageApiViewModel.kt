package com.projecteugene.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.projecteugene.movieapp.adapter.ImageFragmentAdapter
import com.projecteugene.movieapp.utils.BaseViewModel
import com.projecteugene.movieapp.api.ApiService
import com.projecteugene.movieapp.db.MovieDao
import com.projecteugene.movieapp.model.MovieImageApiData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eugene Low
 */
class MovieImageApiViewModel
@Inject
constructor(private val movieDao: MovieDao,
            private val apiService: ApiService): BaseViewModel() {
    private lateinit var disposable: Disposable
    val isLoading:MutableLiveData<Boolean> = MutableLiveData()

    fun call(id: Int, adapter: ImageFragmentAdapter){
        apiService.getMovieImage(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .subscribe(
                { onSuccess(it, adapter) },
                { onError(it) }
            ).addTo(compositeDisposable)
    }

    private fun onStart() {
        isLoading.value = true
    }

    private fun onSuccess(value: MovieImageApiData, adapter: ImageFragmentAdapter) {
        Log.d("movieimages onSuccess", "$value")
        isLoading.value = false
        adapter.update(value.imagePathList)
    }

    private fun onError(throwable: Throwable) {
        Log.e("movieimages onError", "error", throwable)
        isLoading.value = false
    }
}