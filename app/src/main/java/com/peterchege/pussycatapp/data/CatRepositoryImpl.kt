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
package com.peterchege.pussycatapp.data

import com.peterchege.pussycatapp.core.api.CatService
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.api.responses.random_cat_response.RandomImageResponse
import com.peterchege.pussycatapp.core.room.database.CatAppDatabase
import com.peterchege.pussycatapp.core.util.NetworkResult
import com.peterchege.pussycatapp.domain.mappers.toEntity
import com.peterchege.pussycatapp.domain.mappers.toExternalModel
import com.peterchege.pussycatapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatRepositoryImpl(
    private val api: CatService,
    private val db: CatAppDatabase,
) : CatRepository {


    override suspend fun getRandomImage(): NetworkResult<RandomImageResponse> {
        return api.getRandomImage()

    }

    override suspend fun getAllCatBreeds(): NetworkResult<List<CatBreed>> {
        return api.getAllCatBreeds()
    }

    override suspend fun getCatBreedById(breedId: String): NetworkResult<CatBreed> {
        return api.getCatBreedById(breedId = breedId)
    }

    override fun getAllSavedCatBreeds(): Flow<List<CatBreed>> {
        return db.catbreedDao.getAllSavedCatBreeds()
            .map { it.map { it.toExternalModel() } }
    }

    override suspend fun deleteCatBreedById(id: String) {
        db.catbreedDao.deleteCatBreedById(id)
    }

    override suspend fun deleteAllCatBreeds() {
        return db.catbreedDao.deleteAllSavedCatBreeds()
    }

    override suspend fun insertCatBreed(catBreed: CatBreed) {
        db.catbreedDao.saveCatBreed(catBreed = catBreed.toEntity())
    }


}