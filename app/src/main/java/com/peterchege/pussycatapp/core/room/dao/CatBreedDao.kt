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
package com.peterchege.pussycatapp.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.room.entity.CatBreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {

    @Query("SELECT * FROM cat_breed")
    fun getAllSavedCatBreeds(): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM cat_breed WHERE id=:id")
    fun getSavedCatBreedById(id: String):Flow<CatBreedEntity?>

    @Insert
    suspend fun saveCatBreed(catBreed: CatBreedEntity)

    @Query("DELETE FROM cat_breed WHERE id =:id")
    suspend fun deleteCatBreedById(id:String)
    @Query("DELETE FROM cat_breed")
    suspend fun deleteAllSavedCatBreeds()

}