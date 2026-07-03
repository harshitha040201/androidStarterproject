package com.example.usersapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlinapp.presentation.navigation.Screen
import com.example.kotlinapp.presentation.userdetail.UserDetailScreen
import com.example.kotlinapp.presentation.userlist.UserListScreen

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
