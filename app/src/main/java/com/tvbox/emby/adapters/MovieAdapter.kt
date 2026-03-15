package com.tvbox.emby.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tvbox.emby.R
import com.tvbox.emby.models.Movie

/**
 * 影视列表适配器
 */
class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterImageView: ImageView = itemView.findViewById(R.id.poster_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.title_text)
        private val yearTextView: TextView = itemView.findViewById(R.id.year_text)

        fun bind(movie: Movie) {
            // 加载海报图片
            Picasso.get()
                .load(movie.posterUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(posterImageView)

            // 设置标题和年份
            titleTextView.text = movie.title
            yearTextView.text = movie.year

            // 设置点击事件
            itemView.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }
}
