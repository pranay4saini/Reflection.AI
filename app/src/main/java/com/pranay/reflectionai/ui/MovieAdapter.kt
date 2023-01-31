package com.pranay.reflectionai.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pranay.reflectionai.databinding.AdapterMovieBinding
import com.pranay.reflectionai.model.Movie
import com.pranay.reflectionai.viewmodels.MainViewModel

class MovieAdapter:RecyclerView.Adapter<MainViewHolder>() {

    lateinit var viewModel : MainViewModel
    var moviesList = mutableListOf<Movie>()


    fun setMovies(movies: List<Movie>) {
        this.moviesList = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = moviesList[position]

            holder.binding.movieName.text = movie.Title
            holder.binding.cast.text ="""${"Cast"}:- ${movie.Cast}"""
            holder.binding.runtime.text = movie.Runtime
            holder.binding.releaseYear.text = movie.Year
            Glide.with(holder.itemView.context)
                .load(movie.MoviePoster)
                .transform(CircleCrop())
                .into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
//#######################


}
class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}