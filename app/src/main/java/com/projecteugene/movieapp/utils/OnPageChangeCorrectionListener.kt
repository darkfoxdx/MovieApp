package com.projecteugene.movieapp.utils

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * Created by Eugene Low
 */
class OnPageChangeCorrectionListener(private val view: View?): ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
        enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

    private fun enableDisableSwipeRefresh(enable: Boolean) {
        view?.isEnabled = enable
    }
}