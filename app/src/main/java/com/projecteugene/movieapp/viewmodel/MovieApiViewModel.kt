package com.projecteugene.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.projecteugene.movieapp.utils.BaseViewModel
import com.projecteugene.movieapp.api.ApiService
import com.projecteugene.movieapp.db.MovieDao
import com.projecteugene.movieapp.model.MovieInfoApiData
import com.projecteugene.movieapp.model.Optional
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eugene Low
 */
class MovieApiViewModel
@Inject
constructor(private val movieDao: MovieDao,
                        private val apiService: ApiService): BaseViewModel() {
    val isLoading:MutableLiveData<Boolean> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val tagline: MutableLiveData<String> = MutableLiveData()
    val releaseDate: MutableLiveData<String> = MutableLiveData()
    val homepage: MutableLiveData<String> = MutableLiveData()

    fun call(id: Int){
        Observable.fromCallable { Optional(movieDao.getInfo(id)) }
            .concatMap {
                    optional ->
                if (optional.value == null)
                    apiService.getMovie(id).concatMap { data->
                            movieDao.insertInfo(data)
                        Observable.just(data)
                    }
                else
                    Observable.just(optional.value)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .subscribe(
                { onSuccess(it) },
                { onError(it) }
            ).addTo(compositeDisposable)
    }

    private fun onStart() {
        isLoading.value = true
    }

    private fun onSuccess(value: MovieInfoApiData) {
        Log.d("movieinfo onSuccess", "$value")
        isLoading.value = false
        title.value = "Title: ${value.title}"
        tagline.value = "Tagline: ${value.tagline}"
        releaseDate.value = "Release Date: ${value.release_date}"
        homepage.value = "Homepage: ${value.homepage}"

    }

    private fun onError(throwable: Throwable) {
        Log.e("movieinfo onError", "error", throwable)
        isLoading.value = false
    }
}