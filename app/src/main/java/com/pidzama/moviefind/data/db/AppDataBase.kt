package com.pidzama.moviefind.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.utils.TypeConverter

//@Database(entities = [Movie::class], version = 1)
//@TypeConverters(TypeConverter::class)
abstract class AppDataBase : RoomDatabase() {

//    abstract fun movieDao(): MovieDao
}