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

import com.peterchege.pussycatapp.core.util.Constants
import com.peterchege.pussycatapp.core.api.responses.cat_breeds_response.CatBreedsResponse
import com.peterchege.pussycatapp.core.api.responses.cats_by_breeds_response.CatsByBreedResponse
import com.peterchege.pussycatapp.core.api.responses.random_cat_response.RandomImageResponse
import com.peterchege.pussycatapp.core.api.responses.random_cat_response.RandomImageResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

class CatServiceImpl (
    private val client:HttpClient
    ):CatService{
    override suspend fun getRandomImage(): RandomImageResponse {
        return try {
            client.get(
                urlString = "${Constants.BASE_URL}/images/search"
            ) {}.body()
        }catch (e:RedirectResponseException){

            RandomImageResponse()
        }catch (e:ServerResponseException){
            RandomImageResponse()
        }catch (e:ClientRequestException){
           RandomImageResponse()
        }
    }

    override suspend fun getCatBreeds(): CatBreedsResponse {
        return try {
            client.get(
                urlString = "${Constants.BASE_URL}/breeds"
            ) {}.body()
        }catch (e:RedirectResponseException){

            CatBreedsResponse()
        }catch (e:ServerResponseException){
            CatBreedsResponse()
        }catch (e:ClientRequestException){
            CatBreedsResponse()
        }
    }

    override suspend fun getCatsByBreed(limit: Int, breedId: String): CatsByBreedResponse {
        return try {
            client.get(
                urlString = "${Constants.BASE_URL}/breeds/${breedId}"
            ) {
                url {
                    parameters.append(name = "limit",value = limit.toString())
                }
            }.body()
        }catch (e:RedirectResponseException){

            CatsByBreedResponse()
        }catch (e:ServerResponseException){
            CatsByBreedResponse()
        }catch (e:ClientRequestException){
            CatsByBreedResponse()
        }
    }

}