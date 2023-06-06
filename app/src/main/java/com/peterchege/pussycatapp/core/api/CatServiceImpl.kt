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
package com.peterchege.pussycatapp.core.api

import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.api.responses.random_cat_response.RandomImageResponse
import com.peterchege.pussycatapp.core.util.Constants
import com.peterchege.pussycatapp.core.util.NetworkResult
import com.peterchege.pussycatapp.core.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CatServiceImpl(
    private val client: HttpClient
) : CatService {
    override suspend fun getRandomImage(): NetworkResult<RandomImageResponse> = safeApiCall {
        client.get(urlString = "${Constants.BASE_URL}/images/search").body()
    }

    override suspend fun getAllCatBreeds(): NetworkResult<List<CatBreed>> = safeApiCall {
        client.get(urlString = "${Constants.BASE_URL}/breeds").body()
    }

    override suspend fun getCatBreedById(breedId: String): NetworkResult<CatBreed>
    = safeApiCall{
        client.get(urlString = "${Constants.BASE_URL}/breeds/${breedId}").body()
    }

}