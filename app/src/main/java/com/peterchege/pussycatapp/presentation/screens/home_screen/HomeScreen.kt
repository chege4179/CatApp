package com.peterchege.pussycatapp.presentation.screens.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel = getViewModel<HomeScreenViewModel>()

}