package com.pidzama.moviefind.utils.validation

sealed class ValidationResult
object Valid : ValidationResult()
class Invalid(val textError: Int) : ValidationResult()