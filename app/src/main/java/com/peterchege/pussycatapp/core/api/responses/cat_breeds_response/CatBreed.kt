package com.peterchege.pussycatapp.core.api.responses.cat_breeds_response

import kotlinx.serialization.Serializable


/*
Added defaults to every attribute because the api does not return values for
 all cat breeds
 */
@Serializable
data class CatBreed(
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
    val id: String= "",
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
    val weight: Weight = Weight("",""),
    val wikipedia_url: String = ""
)