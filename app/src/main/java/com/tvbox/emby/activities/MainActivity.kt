package com.tvbox.emby.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tvbox.emby.R
import com.tvbox.emby.adapters.MovieAdapter
import com.tvbox.emby.models.Movie
import com.tvbox.emby.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var tvShowRecyclerView: RecyclerView
    private lateinit var varietyRecyclerView: RecyclerView
    private lateinit var animeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiService()
        initViews()
        loadData()
    }

    private fun initViews() {
        movieRecyclerView = findViewById(R.id.movie_recycler_view)
        tvShowRecyclerView = findViewById(R.id.tv_show_recycler_view)
        varietyRecyclerView = findViewById(R.id.variety_recycler_view)
        animeRecyclerView = findViewById(R.id.anime_recycler_view)

        // 设置水平布局管理器
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tvShowRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        varietyRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        animeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // 获取电影列表
                val movies = apiService.getMovies()
                withContext(Dispatchers.Main) {
                    val movieAdapter = MovieAdapter(this@MainActivity, movies) {
                        openDetailActivity(it)
                    }
                    movieRecyclerView.adapter = movieAdapter
                }

                // 获取电视剧列表
                val tvShows = apiService.getTvShows()
                withContext(Dispatchers.Main) {
                    val tvShowAdapter = MovieAdapter(this@MainActivity, tvShows) {
                        openDetailActivity(it)
                    }
                    tvShowRecyclerView.adapter = tvShowAdapter
                }

                // 获取综艺列表
                val varietyShows = apiService.getVarietyShows()
                withContext(Dispatchers.Main) {
                    val varietyAdapter = MovieAdapter(this@MainActivity, varietyShows) {
                        openDetailActivity(it)
                    }
                    varietyRecyclerView.adapter = varietyAdapter
                }

                // 获取动漫列表
                val animeList = apiService.getAnime()
                withContext(Dispatchers.Main) {
                    val animeAdapter = MovieAdapter(this@MainActivity, animeList) {
                        openDetailActivity(it)
                    }
                    animeRecyclerView.adapter = animeAdapter
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error loading data: ${e.message}")
            }
        }
    }

    private fun openDetailActivity(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
