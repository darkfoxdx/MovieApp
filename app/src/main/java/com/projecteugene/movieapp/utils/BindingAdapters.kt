package com.projecteugene.movieapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * Created by Eugene Low
 */

@BindingAdapter("mutable_refreshing")
fun setMutableRefreshing(view: SwipeRefreshLayout, refreshing: MutableLiveData<Boolean>?) {
    val parentActivity: AppCompatActivity? = view.context as? AppCompatActivity
    if(parentActivity != null && refreshing != null) {
        refreshing.observe(parentActivity, Observer { value ->
            view.isRefreshing = value
            view.isEnabled = value
        } )
    }
}

@BindingAdapter("mutable_text")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.context as? AppCompatActivity
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value})
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapter")
fun setAdapter(view: ViewPager, adapter: PagerAdapter) {
    view.adapter = adapter
}

@BindingAdapter("mutable_image_path")
fun setMutableImagePath(view: ImageView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.context as? AppCompatActivity
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            val url = "https://image.tmdb.org/t/p/w500/$value"
            ImageUtil.load(url, view)
        })
    }
}

@BindingAdapter("mutable_image_path")
fun setMutableImagePath(view: ImageView, text: String?) {
    val parentActivity:AppCompatActivity? = view.context as? AppCompatActivity
    if(parentActivity != null && text != null) {
        val url = "https://image.tmdb.org/t/p/w500/$text"
        ImageUtil.load(url, view)
    }
}