/*
 * Copyright 2023 Pussy Cat App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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