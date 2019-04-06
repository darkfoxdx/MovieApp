package com.projecteugene.movieapp.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


/**
 * Created by Eugene Low
 */
object ImageUtil {
    fun load(url: String?, image: ImageView, loading: ProgressBar? = null) {
        if (url.isNullOrEmpty()) {
            image.visibility = View.VISIBLE
            return
        }
        loading?.visibility = View.VISIBLE
        Glide.with(image)
                .setDefaultRequestOptions(RequestOptions()
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL))
                .load(url)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        loading?.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        loading?.visibility = View.GONE
                        return false
                    }

                })
                .into(image)
    }
}