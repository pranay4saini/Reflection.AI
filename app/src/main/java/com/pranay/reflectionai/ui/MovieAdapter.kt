package com.pranay.reflectionai.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pranay.reflectionai.GlideApp
import com.pranay.reflectionai.databinding.AdapterMovieBinding
import com.pranay.reflectionai.model.Movie

class MovieAdapter:RecyclerView.Adapter<MainViewHolder>() {

//    var movieList = mutableListOf<Movie>()
    private val movies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]

            holder.binding.movieName.text = movie.Title
            GlideApp.with(holder.itemView.context)
                .load(movie.MoviePoster)
                .transform(CircleCrop())
                .into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}