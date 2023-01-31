package com.pranay.reflectionai.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pranay.reflectionai.MovieApplication
import com.pranay.reflectionai.R
import com.pranay.reflectionai.databinding.ActivityMainBinding
import com.pranay.reflectionai.viewmodels.MainViewModel
import com.pranay.reflectionai.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private val adapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recycleview.adapter = adapter
        val repository = (application as MovieApplication).movieRepository


        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))
            .get(MainViewModel::class.java)


        mainViewModel.movies.observe(this) {
            adapter.setMovies(it.Movie_List)

        }


    }
}