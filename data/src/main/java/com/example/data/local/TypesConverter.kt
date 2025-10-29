package com.example.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypesConverter {
    @TypeConverter
    fun toList(str: String?): List<String>? {
        val listType = object:TypeToken<List<String>?>(){}.type
        return Gson().fromJson(str, listType)
    }

    @TypeConverter
    fun toString(list: List<String>?): String {
        return Gson().toJson(list)
    }
}