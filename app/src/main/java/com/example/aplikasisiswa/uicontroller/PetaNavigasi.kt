package com.example.aplikasisiswa.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplikasisiswa.modelData.DataSiswa
import com.example.aplikasisiswa.uicontroller.route.DestinasiHome
import com.example.aplikasisiswa.view.HomeScreen

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(),
                 modifier : Modifier) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiHome.route, modifier = Modifier ) {
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = {}, navigateToItemUpdate = {})
        }
    }
}