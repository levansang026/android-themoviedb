package com.bill.android.themoviedb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bill.android.themoviedb.databinding.HomeFragmentBinding
import com.bill.android.themoviedb.repository.MoviesRepository
import com.bill.android.themoviedb.view.adapter.MovieFeedAdapter
import com.bill.android.themoviedb.viewmodel.MovieFeedViewModel

class HomeFragment: Fragment() {

    private val viewModel: MovieFeedViewModel by viewModelsFactory {
        MovieFeedViewModel(MoviesRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@HomeFragment
            binding.viewModel = this@HomeFragment.viewModel
            binding.homeList.adapter = MovieFeedAdapter()
        }
        return binding.root
    }

    private inline fun <reified T : ViewModel> Fragment.viewModelsFactory(crossinline viewModelInitialization: () -> T): Lazy<T> {
        return viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return viewModelInitialization.invoke() as T
                }
            }
        }
    }
}