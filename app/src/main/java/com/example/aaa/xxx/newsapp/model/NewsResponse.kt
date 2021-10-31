package com.example.aaa.xxx.newsapp.model

/**
 * toplam gelen haber bilgisi.
 */
data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)