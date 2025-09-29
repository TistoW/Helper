package com.tisto.helpers.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tisto.helpers.R

fun ImageView.loadImageGlide(
    source: Any?,
    error: Int = R.drawable.helper_asset_placeholder,
    placeHolder: Int = R.drawable.helper_image_loading,
    onFail: (() -> Unit)? = null
) {
    Glide.with(this.context)
        .load(source)
        .error(error)
        .placeholder(placeHolder)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                onFail?.invoke()
                return true
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable?>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .into(this)
}