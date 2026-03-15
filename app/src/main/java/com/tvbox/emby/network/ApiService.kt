package com.tvbox.emby.network

import com.tvbox.emby.models.Movie
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * 网络请求服务类
 */
class ApiService {
    private val client = OkHttpClient()

    /**
     * 获取电影列表
     */
    fun getMovies(): List<Movie> {
        return getMoviesByType("movie")
    }

    /**
     * 获取电视剧列表
     */
    fun getTvShows(): List<Movie> {
        return getMoviesByType("tv")
    }

    /**
     * 获取综艺列表
     */
    fun getVarietyShows(): List<Movie> {
        return getMoviesByType("variety")
    }

    /**
     * 获取动漫列表
     */
    fun getAnime(): List<Movie> {
        return getMoviesByType("anime")
    }

    /**
     * 根据类型获取影视列表
     */
    private fun getMoviesByType(type: String): List<Movie> {
        val movies = mutableListOf<Movie>()
        
        // 模拟数据，实际项目中应该从API获取
        for (i in 1..20) {
            val movie = Movie()
            movie.id = "$type$i"
            movie.title = "$type 标题 $i"
            movie.subTitle = "$type 副标题 $i"
            movie.year = "202${i % 4 + 0}"
            movie.rating = "${(i % 5 + 6)}.${i % 10}"
            movie.genres = when (type) {
                "movie" -> "动作, 科幻, 冒险"
                "tv" -> "剧情, 悬疑, 犯罪"
                "variety" -> "真人秀, 娱乐"
                "anime" -> "动画, 奇幻, 冒险"
                else -> "未知"
            }
            movie.director = "导演 $i"
            movie.actors = "演员 $i, 演员 ${i+1}, 演员 ${i+2}"
            movie.description = "这是 $type 的剧情简介，讲述了一个精彩的故事..."
            movie.posterUrl = "https://picsum.photos/300/450?random=$i"
            movie.backdropUrl = "https://picsum.photos/1280/720?random=$i"
            movie.playUrl = "https://example.com/play/$type$i"
            movie.type = type
            movies.add(movie)
        }
        
        return movies
    }

    /**
     * 获取影视详情
     */
    fun getMovieDetail(id: String): Movie {
        // 模拟数据，实际项目中应该从API获取
        val movie = Movie()
        movie.id = id
        movie.title = "影视标题 $id"
        movie.subTitle = "影视副标题 $id"
        movie.year = "2023"
        movie.rating = "8.5"
        movie.genres = "动作, 科幻, 冒险"
        movie.director = "导演 张三"
        movie.actors = "演员 李四, 演员 王五, 演员 赵六"
        movie.description = "这是一部非常精彩的影视作品，讲述了主人公在未来世界的冒险故事。影片制作精良，演员表现出色，是一部值得观看的好作品。"
        movie.posterUrl = "https://picsum.photos/300/450?random=$id"
        movie.backdropUrl = "https://picsum.photos/1280/720?random=$id"
        movie.playUrl = "https://example.com/play/$id"
        movie.type = "movie"
        return movie
    }
}
