package com.example.tiptime.network.Models

data class NewsHeadlines (
    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String){
    companion object{
        fun listOfNewsHeadlines() : List<NewsHeadlines> {
            return listOf(
                NewsHeadlines(
                    source = Source(id = 1, name = ""),
                    author = "author",
                    title = "title",
                    description = "description",
                    url = "url",
                    urlToImage = "urlToImage",
                    publishedAt = "publishedAt",
                    content = "content"
                )
            )
        }
    }
}
