package com.pidzama.moviefind.di

import android.content.Context
import androidx.room.Room
import com.pidzama.moviefind.data.db.AppDataBase
import com.pidzama.moviefind.data.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieDataBaseModule {

    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "database-name"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao {
        return appDataBase.movieDao()
    }
}