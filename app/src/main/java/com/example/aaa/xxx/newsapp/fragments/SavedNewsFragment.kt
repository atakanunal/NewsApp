package com.example.aaa.xxx.newsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaa.xxx.newsapp.R
import com.example.aaa.xxx.newsapp.adapter.NewsAdapter
import com.example.aaa.xxx.newsapp.viewmodel.BreakingNewsFragmentViewModel
import com.example.aaa.xxx.newsapp.viewmodel.SavedNewsFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_breakingnews.*
import kotlinx.android.synthetic.main.fragment_savednews.*

/**
 * Kaydedilen haberlerin classı.
 */
class SavedNewsFragment : Fragment(R.layout.fragment_savednews) {
    private lateinit var viewModel:SavedNewsFragmentViewModel
    val newsAdapter=NewsAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel= ViewModelProviders.of(this).get(SavedNewsFragmentViewModel::class.java)
        scrollItem()
    }

    private fun scrollItem() {
        val itemTouchHelperCallback=object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=newsAdapter.article[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view!!,"Succesfully deleted article",Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo"){
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }
        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer {articles->
            newsAdapter.RefreshArticle(articles)
        })
    }

    private fun setupRecyclerView() {
        rvSavedNews.adapter = newsAdapter
        rvSavedNews.layoutManager = LinearLayoutManager(context)
    }

}