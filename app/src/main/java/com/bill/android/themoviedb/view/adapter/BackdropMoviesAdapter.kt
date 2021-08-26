package com.bill.android.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bill.android.themoviedb.databinding.MovieBackdropItemBinding
import com.bill.android.themoviedb.model.Movie

class BackdropMoviesAdapter: ListAdapter<Movie, BackdropMoviesAdapter.BackdropMovieHolder>(DiffCallBack) {

    class BackdropMovieHolder(
            private val binding: MovieBackdropItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.backdropPath == newItem.backdropPath
                    && oldItem.posterPath == newItem.posterPath
                    && oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropMovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BackdropMovieHolder(MovieBackdropItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BackdropMovieHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
}