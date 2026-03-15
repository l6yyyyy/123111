package com.tvbox.emby.models

import java.io.Serializable

/**
 * 影视资源模型类
 */
class Movie : Serializable {
    var id: String = ""
    var title: String = ""
    var subTitle: String = ""
    var year: String = ""
    var rating: String = ""
    var genres: String = ""
    var director: String = ""
    var actors: String = ""
    var description: String = ""
    var posterUrl: String = ""
    var backdropUrl: String = ""
    var playUrl: String = ""
    var type: String = ""
    var isCollected: Boolean = false
}
