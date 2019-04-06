package com.projecteugene.movieapp.fragment

import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.projecteugene.movieapp.R
import com.projecteugene.movieapp.databinding.FragmentImageBinding
import com.projecteugene.movieapp.utils.AppKey


/**
 * Created by Eugene Low
 */
class ImageFragment : Fragment() {
    private val image: String?
        get() = arguments?.getString(AppKey.IMAGE_PATH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container?.context),
            R.layout.fragment_image, container, false)
        binding.imagePath = image
        return binding.root
    }

    companion object {
        fun newInstance(string: String): ImageFragment {
            val fragment = ImageFragment()

            val args = Bundle()
            args.putString(AppKey.IMAGE_PATH, string)
            fragment.arguments = args

            return fragment
        }
    }
}