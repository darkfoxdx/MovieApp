package com.projecteugene.movieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projecteugene.movieapp.R
import com.projecteugene.movieapp.databinding.ActivityMainBinding
import com.projecteugene.movieapp.viewmodel.LatestMovieApiViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import com.projecteugene.movieapp.viewmodel.LatestMovieApiViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbar)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        val viewModel = ViewModelProviders
            .of(this, LatestMovieApiViewModelFactory(this))
            .get(LatestMovieApiViewModel::class.java)
        binding.viewModel = viewModel
    }
}
