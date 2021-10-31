package com.example.aaa.xxx.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.aaa.xxx.newsapp.R
import com.example.aaa.xxx.newsapp.databinding.ItemNewsBinding
import com.example.aaa.xxx.newsapp.fragments.BreakingNewsFragmentDirections
import com.example.aaa.xxx.newsapp.model.Article

/**
 * Breakingnews fragment ve detay fragment adaptörü
 */


class NewsAdapter(var article:ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(var view:ItemNewsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<ItemNewsBinding>(inflater,R.layout.item_news,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.view.article=article[position]
        holder.itemView.setOnClickListener{
            val action=BreakingNewsFragmentDirections.actionBreakingNewsFragment2ToDetailFragment(article[position].title.toString(),article[position].description.toString(),article[position],article[position].urlToImage.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun RefreshArticle(articles: List<Article>) {
        article.clear()
        article.addAll(articles)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return article.size
    }
}
