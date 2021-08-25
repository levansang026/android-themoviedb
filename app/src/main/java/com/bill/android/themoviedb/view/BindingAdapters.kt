package com.bill.android.themoviedb.view

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bill.android.themoviedb.model.Movie
import com.bill.android.themoviedb.model.MovieList
import com.bill.android.themoviedb.model.MovieListType
import com.bill.android.themoviedb.view.adapter.BackdropMoviesAdapter
import com.bill.android.themoviedb.view.adapter.MovieFeedAdapter
import com.bill.android.themoviedb.view.adapter.PosterMoviesAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView)
                .load(imgUrl)
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