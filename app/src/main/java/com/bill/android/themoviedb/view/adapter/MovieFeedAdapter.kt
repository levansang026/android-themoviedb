package com.bill.android.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bill.android.themoviedb.databinding.MovieBackdropListItemBinding
import com.bill.android.themoviedb.databinding.MoviePosterListItemBinding
import com.bill.android.themoviedb.model.Movie
import com.bill.android.themoviedb.model.MovieList

class MovieFeedAdapter: ListAdapter<MovieList, RecyclerView.ViewHolder>(DiffCallBack) {

    abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    companion object {
        private const val POSTERTYPE = 0
        private const val BACKDROPTYPE = 1

        object DiffCallBack: DiffUtil.ItemCallback<MovieList>() {
            override fun areItemsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
                return oldItem.type == newItem.type
            }

            override fun areContentsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
                return oldItem.name == newItem.name
                        && oldItem.movies == newItem.movies
            }
        }
    }

    class BackdropListViewHolder(
        private val binding: MovieBackdropListItemBinding
    ): BaseViewHolder<MovieList>(binding.root) {

        override fun bind(item: MovieList) {
            binding.listName = item.name
            binding.movies = item.movies
            binding.list.adapter = BackdropMoviesAdapter()
            binding.executePendingBindings()
        }
    }

    class PosterListViewHolder(
        private val binding: MoviePosterListItemBinding
    ): BaseViewHolder<MovieList>(binding.root){

        override fun bind(item: MovieList) {
            binding.listName = item.name
            binding.movies = item.movies
            binding.list.adapter = PosterMoviesAdapter()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            POSTERTYPE -> {
                PosterListViewHolder(MoviePosterListItemBinding.inflate(layoutInflater))
            }

            else ->  {
                BackdropListViewHolder(MovieBackdropListItemBinding.inflate(layoutInflater))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is PosterListViewHolder -> {
                holder.bind(item)
            }

            is BackdropListViewHolder -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> POSTERTYPE
            else -> BACKDROPTYPE
        }
    }
}