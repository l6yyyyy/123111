package com.tvbox.emby.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.tvbox.emby.R
import com.tvbox.emby.models.Movie
import com.tvbox.emby.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var backdropImageView: ImageView
    private lateinit var posterImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var subTitleTextView: TextView
    private lateinit var yearTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var genresTextView: TextView
    private lateinit var directorTextView: TextView
    private lateinit var actorsTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        apiService = ApiService()
        initViews()
        loadData()
    }

    private fun initViews() {
        backdropImageView = findViewById(R.id.backdrop_image)
        posterImageView = findViewById(R.id.poster_image)
        titleTextView = findViewById(R.id.title_text)
        subTitleTextView = findViewById(R.id.sub_title_text)
        yearTextView = findViewById(R.id.year_text)
        ratingTextView = findViewById(R.id.rating_text)
        genresTextView = findViewById(R.id.genres_text)
        directorTextView = findViewById(R.id.director_text)
        actorsTextView = findViewById(R.id.actors_text)
        descriptionTextView = findViewById(R.id.description_text)
    }

    private fun loadData() {
        val movie = intent.getSerializableExtra("movie") as? Movie
        if (movie != null) {
            displayMovieDetail(movie)
        } else {
            val movieId = intent.getStringExtra("movieId")
            if (movieId != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val movieDetail = apiService.getMovieDetail(movieId)
                        withContext(Dispatchers.Main) {
                            displayMovieDetail(movieDetail)
                        }
                    } catch (e: Exception) {
                        Log.e("DetailActivity", "Error loading movie detail: ${e.message}")
                    }
                }
            }
        }
    }

    private fun displayMovieDetail(movie: Movie) {
        // 加载背景图片
        Picasso.get()
            .load(movie.backdropUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(backdropImageView)

        // 加载海报图片
        Picasso.get()
            .load(movie.posterUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(posterImageView)

        // 设置影视信息
        titleTextView.text = movie.title
        subTitleTextView.text = movie.subTitle
        yearTextView.text = getString(R.string.label_year) + ": " + movie.year
        ratingTextView.text = getString(R.string.label_rating) + ": " + movie.rating
        genresTextView.text = getString(R.string.label_genres) + ": " + movie.genres
        directorTextView.text = getString(R.string.label_director) + ": " + movie.director
        actorsTextView.text = getString(R.string.label_actors) + ": " + movie.actors
        descriptionTextView.text = getString(R.string.label_description) + ": " + movie.description
    }
}
