package com.example.aaa.xxx.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaa.xxx.newsapp.api.NewsService
import com.example.aaa.xxx.newsapp.db.NewsDatabase
import com.example.aaa.xxx.newsapp.model.Article
import com.example.aaa.xxx.newsapp.model.NewsResponse
import com.example.aaa.xxx.newsapp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * Son dakika haberlerin viewmodel classı.
 */

class BreakingNewsFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val disposable= CompositeDisposable()
    val newsData : MutableLiveData<NewsResponse> = MutableLiveData()

    val newsLoad=MutableLiveData<Boolean>()

    fun refreshData(count:Int){
        getDataFromAPI(count)
    }
    fun getDataFromAPI(count:Int){
        disposable.add(
                NewsService.api.getNews("tr",count, Constants.API_KEY)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object: DisposableSingleObserver<NewsResponse>(){
                            override fun onSuccess(t: NewsResponse){
                                newsData.value=t
                                newsLoad.value=false
                            }
                            override fun onError(e:Throwable){
                                newsLoad.value=true
                                e.printStackTrace()
                            }
                        })
        )
        NewsService.api.getNews("tr",count, Constants.API_KEY)
    }
}