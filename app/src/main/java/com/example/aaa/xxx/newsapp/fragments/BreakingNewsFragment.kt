package com.example.aaa.xxx.newsapp.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aaa.xxx.newsapp.R
import com.example.aaa.xxx.newsapp.adapter.NewsAdapter
import com.example.aaa.xxx.newsapp.api.NewsService
import com.example.aaa.xxx.newsapp.db.NewsDatabase
import com.example.aaa.xxx.newsapp.model.NewsResponse
import com.example.aaa.xxx.newsapp.repository.NewsRepository
import com.example.aaa.xxx.newsapp.util.Constants.Companion.API_KEY
import com.example.aaa.xxx.newsapp.viewmodel.BreakingNewsFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_breakingnews.*
import java.util.EnumSet.of

/**
 * Son dakika haberleri burada alınır.
 */
class BreakingNewsFragment : Fragment(R.layout.fragment_breakingnews) {
    private var newsAdapter=NewsAdapter(arrayListOf())
    private lateinit var viewModel:BreakingNewsFragmentViewModel
    var count=1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setHasOptionsMenu(true)
        viewModel=ViewModelProviders.of(this).get(BreakingNewsFragmentViewModel::class.java)
        viewModel.refreshData(count)

        viewModel.newsData.observe(viewLifecycleOwner, Observer {data->
            data?.let {
                rvNewsList.visibility=View.VISIBLE
                newsAdapter.RefreshArticle(data.articles)
            }
        })
    }

    private fun setupRecyclerView() {
        rvNewsList.adapter = newsAdapter
        rvNewsList.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nextPage->{
                if(count==2) count=1
                else count=2
                Toast.makeText(context,"Page Number:$count",Toast.LENGTH_LONG).show()
                viewModel.getDataFromAPI(count)
            }
        }
        return true
    }
}