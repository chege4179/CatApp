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
package com.peterchege.pussycatapp.core.di

import com.peterchege.pussycatapp.core.api.CatService
import com.peterchege.pussycatapp.core.api.CatServiceImpl
import com.peterchege.pussycatapp.data.CatRepositoryImpl
import com.peterchege.pussycatapp.domain.repository.CatRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CatService> {
        CatServiceImpl(client = get())
    }


    single<CatRepository> {
        CatRepositoryImpl(api = get(), db = get())

    }
}