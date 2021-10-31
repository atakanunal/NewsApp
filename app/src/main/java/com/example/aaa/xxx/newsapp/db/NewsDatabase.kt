package com.example.aaa.xxx.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aaa.xxx.newsapp.model.Article

/**
 * Room Database
 */
@Database(
        entities =[Article::class],
        version = 2
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getArticleDao(): NewsDao

    companion object{
        @Volatile
        private var instance:NewsDatabase?=null
        private val lock=Any()

        operator fun invoke(context: Context)=instance?: synchronized(lock){
            instance ?: createDatabase(context).also { instance=it }
        }
        private fun createDatabase(context:Context)= Room.databaseBuilder(context.applicationContext,
        NewsDatabase::class.java,
        "news_db.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}