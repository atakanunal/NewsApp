package com.example.aaa.xxx.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * haber bilgilerini içeren sınıf.
 */
@Entity(
    tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var dataID:Int?=null,
    val author: String?,
    val content:Any?=null,
    val description: String?=null,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable