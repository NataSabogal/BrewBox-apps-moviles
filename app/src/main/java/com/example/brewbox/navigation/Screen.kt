package com.example.brewbox.navigation

sealed class Screen(val route: String) {

    object Splash       : Screen("splash")
    object Onboarding   : Screen("onboarding")
    object Login        : Screen("login")
    object Register     : Screen("register")
    object Plans        : Screen("plans")
    object Payment      : Screen("payment")
    object Delivery     : Screen("delivery")
    object Success      : Screen("success")

    object Home         : Screen("home")
    object Catalog      : Screen("catalog")
    object Box          : Screen("box")
    object Scan         : Screen("scan")
    object CoffeeDetail : Screen("coffee_detail")
    object Profile      : Screen("profile")
}