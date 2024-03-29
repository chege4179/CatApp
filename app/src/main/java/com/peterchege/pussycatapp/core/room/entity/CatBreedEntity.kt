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
package com.peterchege.pussycatapp.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.Weight


@Entity(tableName = "cat_breed")
data class CatBreedEntity(
    @PrimaryKey
    val id: String= "",
    val adaptability: Int= 0,
    val affection_level: Int= 0,
    val alt_names: String = "",
    val child_friendly: Int= 0,
    val country_code: String = "",
    val country_codes: String = "",
    val description: String = "",
    val dog_friendly: Int= 0,
    val energy_level: Int= 0,
    val experimental: Int= 0,
    val grooming: Int= 0,
    val hairless: Int= 0,
    val health_issues: Int= 0,
    val hypoallergenic: Int= 0,

    val indoor: Int= 0,
    val intelligence: Int= 0,
    val lap: Int= 0,
    val life_span: String = "",
    val name: String= "",
    val natural: Int= 0,
    val origin: String,
    val rare: Int= 0,
    val reference_image_id: String= "",
    val rex: Int= 0,
    val shedding_level: Int= 0,
    val short_legs: Int= 0,
    val social_needs: Int= 0,
    val stranger_friendly: Int= 0,
    val suppressed_tail: Int= 0,
    val temperament: String = "",
    val vocalisation: Int = 0,
    val weight: Weight? = Weight("",""),
    val wikipedia_url: String = ""
)