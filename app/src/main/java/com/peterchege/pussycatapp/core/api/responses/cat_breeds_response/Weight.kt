package com.peterchege.pussycatapp.core.api.responses.cat_breeds_response

import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val imperial: String,
    val metric: String
)