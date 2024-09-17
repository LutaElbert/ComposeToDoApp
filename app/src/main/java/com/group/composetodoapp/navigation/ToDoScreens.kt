package com.group.composetodoapp.navigation

enum class ToDoScreens () {
    HomeScreen;
    companion object {
        fun fromRoute(route: String?) : ToDoScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("$route route is not recognize")
            }
    }
}