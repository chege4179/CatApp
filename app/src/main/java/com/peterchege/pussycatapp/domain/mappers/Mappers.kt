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
package com.peterchege.pussycatapp.domain.mappers

import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.Weight
import com.peterchege.pussycatapp.core.room.entity.CatBreedEntity
import com.peterchege.pussycatapp.domain.models.CatBreedUI

fun CatBreed.toEntity(): CatBreedEntity {
    return CatBreedEntity(
        adaptability = adaptability,
        affection_level = affection_level,
        alt_names = alt_names,
        child_friendly = child_friendly,
        country_code = country_code,
        country_codes = country_codes,
        description = description,
        dog_friendly = dog_friendly,
        energy_level = energy_level,
        experimental = experimental,
        grooming = grooming,
        hairless = hairless,
        health_issues = health_issues,
        hypoallergenic = hypoallergenic,
        id = id,
        indoor = indoor,
        intelligence = intelligence,
        lap = lap,
        life_span = life_span,
        name = name,
        natural = natural,
        origin = origin,
        rare = rare,
        reference_image_id = reference_image_id,
        rex = rex,
        shedding_level = shedding_level,
        short_legs = short_legs,
        social_needs = social_needs,
        stranger_friendly = stranger_friendly,
        suppressed_tail = suppressed_tail,
        temperament = temperament,
        vocalisation = vocalisation,
        weight = weight,
        wikipedia_url = wikipedia_url

    )
}



fun CatBreedEntity.toExternalModel(): CatBreed {
    return CatBreed(
        adaptability = adaptability,
        affection_level = affection_level,
        alt_names = alt_names,
        child_friendly = child_friendly,
        country_code = country_code,
        country_codes = country_codes,
        description = description,
        dog_friendly = dog_friendly,
        energy_level = energy_level,
        experimental = experimental,
        grooming = grooming,
        hairless = hairless,
        health_issues = health_issues,
        hypoallergenic = hypoallergenic,
        id = id,
        indoor = indoor,
        intelligence = intelligence,
        lap = lap,
        life_span = life_span,
        name = name,
        natural = natural,
        origin = origin,
        rare = rare,
        reference_image_id = reference_image_id,
        rex = rex,
        shedding_level = shedding_level,
        short_legs = short_legs,
        social_needs = social_needs,
        stranger_friendly = stranger_friendly,
        suppressed_tail = suppressed_tail,
        temperament = temperament,
        vocalisation = vocalisation,
        weight = weight,
        wikipedia_url = wikipedia_url

    )
}





fun CatBreed.toUIModel(isSavedInDb:Boolean): CatBreedUI {
    return CatBreedUI(
        isSaved = isSavedInDb,
        adaptability = adaptability,
        affection_level = affection_level,
        alt_names = alt_names,
        child_friendly = child_friendly,
        country_code = country_code,
        country_codes = country_codes,
        description = description,
        dog_friendly = dog_friendly,
        energy_level = energy_level,
        experimental = experimental,
        grooming = grooming,
        hairless = hairless,
        health_issues = health_issues,
        hypoallergenic = hypoallergenic,
        id = id,
        indoor = indoor,
        intelligence = intelligence,
        lap = lap,
        life_span = life_span,
        name = name,
        natural = natural,
        origin = origin,
        rare = rare,
        reference_image_id = reference_image_id,
        rex = rex,
        shedding_level = shedding_level,
        short_legs = short_legs,
        social_needs = social_needs,
        stranger_friendly = stranger_friendly,
        suppressed_tail = suppressed_tail,
        temperament = temperament,
        vocalisation = vocalisation,
        weight = weight,
        wikipedia_url = wikipedia_url

    )
}

fun CatBreedUI.toCatBreed(): CatBreed {
    return CatBreed(
        adaptability = adaptability,
        affection_level = affection_level,
        alt_names = alt_names,
        child_friendly = child_friendly,
        country_code = country_code,
        country_codes = country_codes,
        description = description,
        dog_friendly = dog_friendly,
        energy_level = energy_level,
        experimental = experimental,
        grooming = grooming,
        hairless = hairless,
        health_issues = health_issues,
        hypoallergenic = hypoallergenic,
        id = id,
        indoor = indoor,
        intelligence = intelligence,
        lap = lap,
        life_span = life_span,
        name = name,
        natural = natural,
        origin = origin,
        rare = rare,
        reference_image_id = reference_image_id,
        rex = rex,
        shedding_level = shedding_level,
        short_legs = short_legs,
        social_needs = social_needs,
        stranger_friendly = stranger_friendly,
        suppressed_tail = suppressed_tail,
        temperament = temperament,
        vocalisation = vocalisation,
        weight = weight,
        wikipedia_url = wikipedia_url

    )
}