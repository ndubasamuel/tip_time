package com.example.tiptime.network.Models

import com.google.gson.annotations.SerializedName

final data class ApiResponse (
    @SerializedName("status")
    var status: String = (""),
    @SerializedName("totalResults")
    var results: Int? = null,

    @SerializedName("articles")
    var news: List<News>? = null
)
data class News(
    @SerializedName("source"      ) var source: Source,
    @SerializedName("author"      ) var author: String?,
    @SerializedName("title"       ) var title: String?,
    @SerializedName("description" ) var description: String?,
    @SerializedName("url"         ) var url: String?,
    @SerializedName("urlToImage"  ) var urlToImage: String?,
    @SerializedName("publishedAt" ) var publishedAt: String?,
    @SerializedName("content"     ) var content: String?
)

data class Source(

    @SerializedName("id"   ) var id   : String?,
    @SerializedName("name" ) var name : String?

)











