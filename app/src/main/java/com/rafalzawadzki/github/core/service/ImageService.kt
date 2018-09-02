package com.rafalzawadzki.github.core.service

import android.content.Context
import android.widget.ImageView
import com.rafalzawadzki.github.R
import com.rafalzawadzki.github.core.data.models.User
import com.rafalzawadzki.github.core.di.qualifiers.ForApplication
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageService
@Inject constructor(@ForApplication context: Context) {

    private val instance: Picasso = Picasso.get()

    fun loadUserImage(user: User, imageView: ImageView) {
        val requestCreator = if (user.avatarUrl.isBlank())
            instance.load(R.color.grey_three)
        else
            instance.load(user.avatarUrl)

        requestCreator
                .placeholder(R.color.grey_three)
                .fit()
                .centerCrop()
                .into(imageView)
    }
}
