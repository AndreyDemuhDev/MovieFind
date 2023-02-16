package com.pidzama.moviefind.repository

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val GLOBAL_PREF = "global_pref"
private const val IS_FIRST_START = "is_first_start"

@Singleton
class SharedPreferenceRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val globalPreference = context.getSharedPreferences(GLOBAL_PREF, Context.MODE_PRIVATE)

    fun isFirstStart(): Boolean {
        return globalPreference.getBoolean(IS_FIRST_START, true)
    }

    fun setNoFirstStart() {
        globalPreference.edit {
            putBoolean(IS_FIRST_START, false)
        }
    }
}