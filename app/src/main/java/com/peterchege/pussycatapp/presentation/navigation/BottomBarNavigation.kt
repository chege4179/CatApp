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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.peterchege.pussycatapp.core.util.Screens
import com.peterchege.pussycatapp.domain.models.BottomNavItem
import com.peterchege.pussycatapp.presentation.screens.home.HomeScreen
import com.peterchege.pussycatapp.presentation.screens.saved.SavedCatBreedScreen

@ExperimentalMaterial3Api
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEachIndexed { index, item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                },
                label = { Text(text = item.name) },
                selected = selected,
                onClick = { onItemClick(item) }
            )
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun DashBoardScreen(
    navHostController: NavHostController,
) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Screens.HOME_SCREEN,
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Saved Cats",
                        route = Screens.SAVED_CAT_SCREEN,
                        icon = Icons.Default.Favorite
                    ),
                ),
                navController = bottomNavController,
                onItemClick = {
                    bottomNavController.navigate(it.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(bottomNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screens.HOME_SCREEN
            ) {
                composable(
                    route = Screens.HOME_SCREEN
                ) {
                    HomeScreen(
                        navigateToCatBreedScreen = {
                            navHostController.navigate(Screens.CAT_BREED_SCREEN + "/${it}")
                        }

                    )
                }
                composable(
                    route = Screens.SAVED_CAT_SCREEN
                ) {
                    SavedCatBreedScreen(
                        navigateToCatBreedScreen = {
                            navHostController.navigate(Screens.CAT_BREED_SCREEN + "/${it}")
                        }
                    )
                }

            }
        }

    }
}