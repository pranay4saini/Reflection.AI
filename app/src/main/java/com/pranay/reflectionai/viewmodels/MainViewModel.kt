package com.pranay.reflectionai.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pranay.reflectionai.model.Movie
import com.pranay.reflectionai.repository.Repository
import com.pranay.reflectionai.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {


    init{
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllMovies()
        }

    }
    val movies:LiveData<MoviesResponse>
        get() = repository.movies




}