package com.projecteugene.movieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.projecteugene.movieapp.R
import com.projecteugene.movieapp.activity.DetailActivity
import com.projecteugene.movieapp.databinding.ItemMovieBinding
import com.projecteugene.movieapp.model.MovieData
import com.projecteugene.movieapp.viewmodel.MovieItem

/**
 * Created by Eugene Low
 */

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var list:List<MovieData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return if(::list.isInitialized) list.size else 0
    }

    fun updateList(list: List<MovieData>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = MovieItem()
        fun bind(item :MovieData){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }
}