package com.example.kotlinapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlinapp.detail.UserDetailScreen

@Composable
fun UsersNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    ) {
        composable(Screen.UserList.route) {
            UserListScreen(
                onUserClick = { userId ->
                    navController.navigate(Screen.UserDetail.createRoute(userId))
                }
            )
        }

        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(navArgument(Screen.UserDetail.ARG_USER_ID) { type = NavType.IntType })
        ) {
            UserDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
