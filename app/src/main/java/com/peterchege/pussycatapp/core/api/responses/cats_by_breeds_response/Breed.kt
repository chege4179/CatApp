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
package com.peterchege.pussycatapp.core.api.responses.cats_by_breeds_response

import kotlinx.serialization.Serializable

@Serializable
data class Breed(
    val adaptability: Int,
    val affection_level: Int,
    val bidability: Int?,
    val cat_friendly: Int,
    val cfa_url: String,
    val child_friendly: Int,
    val country_code: String,
    val country_codes: String,
    val description: String,
    val dog_friendly: Int,
    val energy_level: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    val health_issues: Int,
    val hypoallergenic: Int,
    val id: String,
    val indoor: Int,
    val intelligence: Int,
    val lap: Int,
    val life_span: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    val reference_image_id: String,
    val rex: Int,
    val shedding_level: Int,
    val short_legs: Int,
    val social_needs: Int,
    val stranger_friendly: Int,
    val suppressed_tail: Int,
    val temperament: String,
    val vcahospitals_url: String,
    val vetstreet_url: String,
    val vocalisation: Int,
    val weight: Weight,
    val wikipedia_url: String
)