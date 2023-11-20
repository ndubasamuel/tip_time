package com.example.tiptime.network.Models

import com.google.gson.annotations.SerializedName

final data class ApiResponse (
    @SerializedName("status")
    var status: String = (""),
    @SerializedName("totalResults")
    var results: Int? = null,

    val news: List<News>? = null,
)
data class News(
    @SerializedName("source"      ) var source: Source = Source(),
    @SerializedName("author"      ) var author: String? = null,
    @SerializedName("title"       ) var title: String? = null,
    @SerializedName("description" ) var description: String? = null,
    @SerializedName("url"         ) var url: String? = null,
    @SerializedName("urlToImage"  ) var urlToImage: String? = null,
    @SerializedName("publishedAt" ) var publishedAt: String? = null,
    @SerializedName("content"     ) var content: String? = null
)
data class Source(

    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null

)











