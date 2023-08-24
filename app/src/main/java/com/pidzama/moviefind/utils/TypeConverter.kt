package com.pidzama.moviefind.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pidzama.moviefind.data.model.movies.Links

class TypeConverter {

    @TypeConverter
    fun fromStringToListGenres(value: String): List<String> {
        return ArrayList()
    }

    @TypeConverter
    fun fromListGenresToString(list: List<String>): String {
        return ""
    }
}