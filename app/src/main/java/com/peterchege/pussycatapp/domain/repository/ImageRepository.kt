package com.peterchege.pussycatapp.domain.repository

import com.peterchege.pussycatapp.core.api.responses.RandomImageResponse

interface ImageRepository {

    suspend fun getRandomImage():RandomImageResponse
}