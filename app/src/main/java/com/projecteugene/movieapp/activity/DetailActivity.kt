package com.projecteugene.movieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.projecteugene.movieapp.R
import com.projecteugene.movieapp.databinding.ActivityDetailBinding
import com.projecteugene.movieapp.utils.AppKey
import com.projecteugene.movieapp.viewmodel.MovieApiViewModel
import com.projecteugene.movieapp.viewmodel.MovieImageApiViewModel

import kotlinx.android.synthetic.main.activity_main.*
import com.projecteugene.movieapp.utils.OnPageChangeCorrectionListener
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.projecteugene.movieapp.adapter.ImageFragmentAdapter
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        AndroidInjection.inject(this)
        val adapter = ImageFragmentAdapter(supportFragmentManager)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_detail
        )
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val imageViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieImageApiViewModel::class.java)
        imageViewModel.call(id, adapter)
        binding.adapter = adapter
        binding.imageViewModel = imageViewModel
        binding.viewPager.addOnPageChangeListener(OnPageChangeCorrectionListener(binding.swipeRefreshLayout))
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)

        val infoViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieApiViewModel::class.java)
        infoViewModel.call(id)
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
