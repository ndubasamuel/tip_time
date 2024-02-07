package com.example.tiptime.DB.Room

import androidx.room.TypeConverter
import com.example.tiptime.Model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


//sorting the different types of data as JSON within the DB

class DataConverter {

    @TypeConverter
    fun fromSource(value: Source): String{
        val gson = Gson()
        val type = object :TypeToken<Source>() {}.type
        return gson.toJson(value, type)

    }

    @TypeConverter
    fun toSource(value: String):Source {
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(value, type)
    }

}