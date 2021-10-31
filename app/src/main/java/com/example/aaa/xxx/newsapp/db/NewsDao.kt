package com.example.aaa.xxx.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.aaa.xxx.newsapp.model.Article
/**
 * Data access Object
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article:Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article:Article)
}