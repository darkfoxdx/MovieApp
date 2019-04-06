package com.projecteugene.movieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.projecteugene.movieapp.R
import com.projecteugene.movieapp.databinding.ActivityDetailBinding
import com.projecteugene.movieapp.utils.AppKey
import com.projecteugene.movieapp.viewmodel.MovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieApiViewModelFactory
import com.projecteugene.movieapp.viewmodel.MovieImageApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieImageApiViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import com.projecteugene.movieapp.utils.OnPageChangeCorrectionListener
import android.view.MenuItem
import com.sothree.slidinguppanel.SlidingUpPanelLayout


class DetailActivity : AppCompatActivity() {

    private val id: Int
        get() = intent.getIntExtra(AppKey.ID, 0)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_detail
        )
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val imageViewModel = ViewModelProviders
            .of(this, MovieImageApiViewModelFactory(this, id, supportFragmentManager))
            .get(MovieImageApiViewModel::class.java)
        binding.imageViewModel = imageViewModel
        binding.viewPager.addOnPageChangeListener(OnPageChangeCorrectionListener(binding.swipeRefreshLayout))
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)

        val infoViewModel = ViewModelProviders
            .of(this, MovieApiViewModelFactory(this, id))
            .get(MovieApiViewModel::class.java)
        binding.infoViewModel = infoViewModel

    }

    override fun onBackPressed() {
        if (binding.slidingLayout.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            binding.slidingLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            super.onBackPressed()
        }
    }
}
