package de.br.news.showcase.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class NewsItemsBindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, imageUrl: String) {
            Glide.with(view).load(imageUrl).into(view)
        }
    }

}