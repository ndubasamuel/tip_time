package com.example.tiptime.network.Models

import com.google.gson.annotations.SerializedName

final data class ApiResponse (
    @SerializedName("status")
    var status: String = (""),
    @SerializedName("totalResults")
    var results: Int? = null,

    val news: List<News> = listOf()
)
data class News(
    @SerializedName("articles"      ) var source: Source = Source(),
    @SerializedName("author"      ) var author: String? = "",
    @SerializedName("title"       ) var title: String? = "",
    @SerializedName("description" ) var description: String? = "",
    @SerializedName("url"         ) var url: String? = "",
    @SerializedName("urlToImage"  ) var urlToImage: String? = "",
    @SerializedName("publishedAt" ) var publishedAt: String? = "",
    @SerializedName("content"     ) var content: String? = ""
)

data class Source(

    @SerializedName("id"   ) var id   : String? = "",
    @SerializedName("name" ) var name : String? = ""

)











