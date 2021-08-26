package com.bill.android.themoviedb.view

import android.graphics.drawable.Drawable
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bill.android.themoviedb.R
import com.bill.android.themoviedb.model.Movie
import com.bill.android.themoviedb.model.MovieList
import com.bill.android.themoviedb.model.MovieListType
import com.bill.android.themoviedb.view.adapter.BackdropMoviesAdapter
import com.bill.android.themoviedb.view.adapter.MovieFeedAdapter
import com.bill.android.themoviedb.view.adapter.PosterMoviesAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView)
            .load(imgUrl)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)

            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    (imgView.parent as ViewGroup).findViewById<TextView>(R.id.textView)?.visibility = INVISIBLE
                    return false
                }

            })
            .transition(withCrossFade())
            .into(imgView)
    }
}

@BindingAdapter("posterMovieList")
fun bindPosterRecyclerView(view: RecyclerView, data: List<Movie>) {
    val adapter = view.adapter as PosterMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("backdropMovieList")
fun bindBackdropRecyclerView(view: RecyclerView, data: List<Movie>) {
    val adapter = view.adapter as BackdropMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieLists")
fun bindFeedRecyclerView(view: RecyclerView, data: List<MovieList>) {
    val adapter = view.adapter as MovieFeedAdapter
    adapter.submitList(data)
}