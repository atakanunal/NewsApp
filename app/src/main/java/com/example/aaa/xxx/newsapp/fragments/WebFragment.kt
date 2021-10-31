package com.example.aaa.xxx.newsapp.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.aaa.xxx.newsapp.R
import com.example.aaa.xxx.newsapp.db.NewsDatabase
import com.example.aaa.xxx.newsapp.model.Article
import com.example.aaa.xxx.newsapp.repository.NewsRepository
import com.example.aaa.xxx.newsapp.viewmodel.BreakingNewsFragmentViewModel
import com.example.aaa.xxx.newsapp.viewmodel.WebFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * webview clası.
 */
class WebFragment : Fragment(R.layout.fragment_web) {
    lateinit var viewModel:WebFragmentViewModel
    val args:WebFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article=args.article
        viewModel=ViewModelProviders.of(this).get(WebFragmentViewModel::class.java)
        webView.apply {
            webViewClient= WebViewClient()
            loadUrl(article.url.toString())
        }
        fab.setOnClickListener{
            viewModel.saveArticle(article)
            Snackbar.make(view,"Article saved succesfully",Snackbar.LENGTH_LONG).show()
        }
    }
}