package com.pranay.reflectionai.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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



        val repository = (application as MovieApplication).movieRepository
        //endless scroll
        var isLoading = false
        var isLastPage = false
        var isScrolling = false


        val scrollListener = object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount


                val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                val isAtLastPage = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                val isNotAtBeginning = firstVisibleItemPosition >= 0
                val isTotalMoreThanVisible = totalItemCount >= 10
                val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastPage && isNotAtBeginning &&
                        isTotalMoreThanVisible &&isScrolling
                if(shouldPaginate){
                    mainViewModel.movies
                    isScrolling = false
                }
            }
        }


        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))
            .get(MainViewModel::class.java)


        mainViewModel.movies.observe(this) {

            adapter.setMovies(it.Movie_List)
            binding.floatingActionButton.setOnClickListener {
                mainViewModel.getMovies2()

            }

        }

        binding.recycleview.adapter = adapter
        binding.recycleview.addOnScrollListener(scrollListener)




    }

}