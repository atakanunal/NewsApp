package com.example.aaa.xxx.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaa.xxx.newsapp.db.NewsDatabase
import com.example.aaa.xxx.newsapp.model.Article
import com.example.aaa.xxx.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

/***
 * web fragmentın viewmodel classı.
 */

class WebFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private var newsRepository=NewsRepository(NewsDatabase(application))
    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.insertArticle(article)
    }
}