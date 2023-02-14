package com.peterchege.pussycatapp.domain.use_case

import com.peterchege.pussycatapp.core.api.responses.RandomImageResponse
import com.peterchege.pussycatapp.domain.repository.ImageRepository

class GetRandomImageUseCase(
    private val repository: ImageRepository
) {


    suspend operator fun invoke(): RandomImageResponse {
        return repository.getRandomImage()
    }
}