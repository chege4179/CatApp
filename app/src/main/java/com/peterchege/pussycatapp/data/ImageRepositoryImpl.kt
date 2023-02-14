package com.peterchege.pussycatapp.data

import com.peterchege.pussycatapp.core.api.CatApi
import com.peterchege.pussycatapp.core.api.responses.RandomImageResponse
import com.peterchege.pussycatapp.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val api: CatApi,
) : ImageRepository {


    override suspend fun getRandomImage(): RandomImageResponse {
        TODO("Not yet implemented")
    }


}