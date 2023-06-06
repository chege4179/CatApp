package com.peterchege.pussycatapp.core.util

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T : Any> safeApiCall(
    apiCall: suspend () -> T
): NetworkResult<T> = try {
    NetworkResult.Success(apiCall.invoke())
} catch (throwable: Throwable) {

    when (throwable) {
        is ServerResponseException, is NoTransformationFoundException -> {
            NetworkResult.Error("Server error", exc = throwable)
        }
        is ConnectTimeoutException -> {
            NetworkResult.Error("Network error", exc = throwable, networkError = true)
        }
        else -> {
            NetworkResult.Error("Client error", exc = throwable)
        }
    }
}

sealed interface NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>
    data class Error(
        val message: String,
        val networkError: Boolean = false,
        val exc: Throwable? = null
    ) : NetworkResult<Nothing>

    data class Loading<out T : Any>(val data: T?) : NetworkResult<T>
    object Empty : NetworkResult<Nothing>
}