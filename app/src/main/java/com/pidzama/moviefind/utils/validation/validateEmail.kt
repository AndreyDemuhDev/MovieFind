package com.pidzama.moviefind.utils.validation

import android.util.Patterns
import com.pidzama.moviefind.R

fun validateEmail(email: String): ValidationResult {
    return when {
        email.isBlank() -> {
            Invalid(R.string.email_blank)
        }
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            Invalid(R.string.email_wrong_type)
        }
        else -> Valid
    }
}