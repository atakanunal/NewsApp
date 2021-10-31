package com.example.aaa.xxx.newsapp.db

import androidx.room.TypeConverter
import com.example.aaa.xxx.newsapp.model.Source

/**
 * room converter.
 */
class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String) : Source{
        return Source(name,name)
    }
    @TypeConverter
    fun fromContent(content:Any):String{
        return content.toString()
    }
    @TypeConverter
    fun toContent(name:String) : Any{
        return Any()
    }


}