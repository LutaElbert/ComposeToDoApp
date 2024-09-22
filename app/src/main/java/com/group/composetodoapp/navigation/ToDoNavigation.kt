package com.group.composetodoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group.composetodoapp.screens.homepage.Homepage

@Composable
fun ToDoNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ToDoScreens.HomeScreen.name) {
        composable(ToDoScreens.HomeScreen.name) {
            Homepage()
        }
    }
}