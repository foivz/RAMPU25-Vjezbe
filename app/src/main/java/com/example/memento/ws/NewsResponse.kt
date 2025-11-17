package com.example.memento.ws

data class NewsResponse(
    var count: Int,
    var results: ArrayList<NewsItem>
)
