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
package com.peterchege.pussycatapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.peterchege.pussycatapp.core.util.Screens
import com.peterchege.pussycatapp.presentation.screens.cat_breed.CatBreedScreen
import com.peterchege.pussycatapp.presentation.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController =navHostController,
        startDestination = Screens.DASHBOARD_SCREEN,
    ){
        composable(route = Screens.DASHBOARD_SCREEN){
            DashBoardScreen(navHostController = navHostController)
        }
        composable(
            route = Screens.CAT_BREED_SCREEN + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })

        ){
            val id = it.arguments?.getString("id") ?: throw IllegalStateException("Breed ID missing.")
            CatBreedScreen(navController = navHostController, breedId = id)
        }
    }

}