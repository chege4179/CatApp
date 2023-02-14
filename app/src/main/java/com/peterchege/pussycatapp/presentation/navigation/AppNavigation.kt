package com.peterchege.pussycatapp.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.pussycatapp.core.util.Screens
import com.peterchege.pussycatapp.presentation.screens.home_screen.HomeScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController =navHostController,
        startDestination = Screens.HOME_SCREEN,
    ){
        composable(Screens.HOME_SCREEN){
            HomeScreen(navController = navHostController)
        }

    }

}