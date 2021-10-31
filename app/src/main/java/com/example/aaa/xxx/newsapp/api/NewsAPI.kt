package com.example.aaa.xxx.newsapp.api

import com.example.aaa.xxx.newsapp.model.NewsResponse
import com.example.aaa.xxx.newsapp.util.Constants.Companion.API_KEY
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * son dakika haberleri çekilir.
 */
interface NewsAPI {
    @GET("v2/top-headlines")
    fun getNews(
            @Query("country")
            countryCode:String="tr",
            @Query("page")
            pageNumber:Int=1,
            @Query("apiKey")
            apiKey:String=API_KEY
    ): Single<NewsResponse>
}