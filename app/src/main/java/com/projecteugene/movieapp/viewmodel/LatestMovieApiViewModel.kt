package com.projecteugene.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.projecteugene.movieapp.adapter.MovieAdapter
import com.projecteugene.movieapp.utils.BaseViewModel
import com.projecteugene.movieapp.api.ApiService
import com.projecteugene.movieapp.db.MovieDao
import com.projecteugene.movieapp.model.MovieData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eugene Low
 */
class LatestMovieApiViewModel
@Inject
constructor(private val movieDao: MovieDao, private val apiService: ApiService): BaseViewModel() {

    val isLoading:MutableLiveData<Boolean> = MutableLiveData()
    val adapter = MovieAdapter()

    init{
        call()
    }

    private fun call(){
        Observable.fromCallable { movieDao.all }
            .concatMap {
                    list ->
                if(list.isEmpty())
                    apiService.getLatest().concatMap { data->
                            movieDao.insertAll(data.results)
                        Observable.just(data.results)
                    }
                else
                    Observable.just(list)
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

    private fun onSuccess(value: List<MovieData>) {
        Log.d("discover onSuccess", "$value")
        isLoading.value = false
        adapter.updateList(value)
    }

    private fun onError(throwable: Throwable) {
        Log.e("discover onError", "error", throwable)
        isLoading.value = false
    }
}