package com.example.brewbox.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewbox.viewmodel.AuthViewModel
import androidx.compose.runtime.collectAsState
import com.example.brewbox.ui.screens.*

data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home",    Icons.Default.Home,        Screen.Home.route),
    BottomNavItem("Catalog", Icons.Default.Search,      Screen.Catalog.route),
    BottomNavItem("My Box",  Icons.Default.ShoppingBag, Screen.Box.route),
    BottomNavItem("Profile", Icons.Default.Person,      Screen.Profile.route),
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState(initial = false)
    val loginError by authViewModel.loginError.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavRoutes = listOf(
        Screen.Home.route,
        Screen.Catalog.route,
        Screen.Box.route,
        Screen.Profile.route
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Splash.route) {
                SplashScreen(
                    onContinue = {
                        if (isLoggedIn) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Splash.route) { inclusive = true }
                            }
                        } else {
                            navController.navigate(Screen.Onboarding.route)
                        }
                    }
                )
            }

            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    onContinue = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    onSignIn = { email, password ->
                        authViewModel.login(email, password) { success ->
                            if (success) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            }
                        }
                    },
                    onCreateAccount = {
                        navController.navigate(Screen.Register.route)
                    },
                    errorMessage = loginError,
                    onClearError = { authViewModel.clearError() }
                )
            }

            composable(Screen.Register.route) {
                RegisterScreen(
                    onRegister = { email, name, address, birthday, password ->
                        authViewModel.register(email, name, address, birthday, password)
                        navController.navigate(Screen.Plans.route)
                    },
                    onBackToLogin = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Plans.route) {
                PlansScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onSelectPlan = {
                        navController.navigate(Screen.Payment.route)
                    }
                )
            }

            composable(Screen.Payment.route) {
                PaymentScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onConfirm = {
                        navController.navigate(Screen.Delivery.route)
                    }
                )
            }

            composable(Screen.Delivery.route) {
                DeliveryScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onConfirm = {
                        navController.navigate(Screen.Success.route)
                    }
                )
            }

            composable(Screen.Success.route) {
                SuccessScreen(
                    onGoHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    onTrackOrder = {
                        navController.navigate(Screen.Box.route)
                    },
                    onSeeHistory = {
                        navController.navigate(Screen.Catalog.route)
                    },
                    onCoffeeDetail = {
                        navController.navigate(Screen.CoffeeDetail.route)
                    }
                )
            }

            composable(Screen.Catalog.route) {
                CatalogScreen(
                    onCoffeeDetail = { coffeeId ->
                        navController.navigate(Screen.CoffeeDetail.route)
                    }
                )
            }

            composable(Screen.Box.route) {
                BoxScreen(
                    onScan = {
                        navController.navigate(Screen.Scan.route)
                    }
                )
            }

            composable(Screen.Scan.route) {
                ScanScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.CoffeeDetail.route) {
                CoffeeDetailScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Profile.route) {
                val currentUser by authViewModel.currentUser.collectAsState(initial = null)
                ProfileScreen(
                    userData = currentUser,
                    onBack = {
                        navController.popBackStack()
                    },
                    onSignOut = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
