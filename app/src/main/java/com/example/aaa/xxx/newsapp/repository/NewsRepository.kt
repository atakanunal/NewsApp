package com.example.aaa.xxx.newsapp.repository

import com.example.aaa.xxx.newsapp.db.NewsDatabase
import com.example.aaa.xxx.newsapp.model.Article


class NewsRepository(
    val db:NewsDatabase
) {
    suspend fun insertArticle(article:Article)=db.getArticleDao().insertArticle(article)
    fun getSavedNews()=db.getArticleDao().getAllArticles()
    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)

}