package com.example.kotlinapp

sealed class Screen(val route: String) {
    data object UserList : Screen("userList")

    data object UserDetail : Screen("userDetail/{userId}") {
        const val ARG_USER_ID = "userId"
        fun createRoute(userId: Int) = "userDetail/$userId"
    }
}