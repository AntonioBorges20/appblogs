package com.example.appblog.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.appblog.R
import com.example.appblog.core.Resource
import com.example.appblog.data.remote.home.HomeScreenDataSource
import com.example.appblog.databinding.FragmentHomeScreenBinding
import com.example.appblog.domain.home.HomeScreenRepoImpI
import com.example.appblog.presentation.home.HomeScreenViewModel
import com.example.appblog.presentation.home.HomeScreenViewModelFactory
import com.example.appblog.ui.home.adapter.HomeScreenAdapter


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel>{
        HomeScreenViewModelFactory(
            HomeScreenRepoImpI(
            HomeScreenDataSource()
        )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentHomeScreenBinding.bind(view)
        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer {
            result->
            when(result){
                is Resource.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }
                is Resource.Success->{
                    binding.progressBar.visibility=View.GONE
                    binding.rvHome.adapter=HomeScreenAdapter(result.data)
                }
                is Resource.Failure->{
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }
}