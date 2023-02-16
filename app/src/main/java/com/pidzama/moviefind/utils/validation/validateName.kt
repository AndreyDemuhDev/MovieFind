package com.pidzama.moviefind.utils.validation

import com.pidzama.moviefind.R

fun validateName(name : String) : ValidationResult {
    return when {
        name.isBlank() -> {
            Invalid(R.string.name_blank)
        }else -> Valid
    }
}