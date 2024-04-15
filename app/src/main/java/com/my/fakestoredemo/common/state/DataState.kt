package com.my.fakestoredemo.common.state

data class DataState<T>(
    val data: T? = null,
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val message: String? = null,
    val errorMessage: String? = null
) {

    companion object {
        fun <T> loading(isLoading: Boolean): DataState<T> {
            return DataState(
                isLoading = isLoading
            )
        }


        fun <T> success(
            data: T?
        ): DataState<T> {
            return DataState(
                data = data, success = true
            )
        }


        fun <T> error(error_message: String): DataState<T> {
            return DataState(
                success = false,
                errorMessage = error_message
            )
        }
    }
}


