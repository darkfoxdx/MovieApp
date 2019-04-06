package com.projecteugene.movieapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.projecteugene.movieapp.fragment.ImageFragment

/**
 * Created by Eugene Low
 */
class ImageFragmentAdapter(fm: FragmentManager,
                           private var list: List<String> = ArrayList()): FragmentStatePagerAdapter(fm) {
    fun update(list: List<String>?) {
        this.list = list?:ArrayList()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemPosition(item: Any): Int {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return PagerAdapter.POSITION_NONE
    }

}