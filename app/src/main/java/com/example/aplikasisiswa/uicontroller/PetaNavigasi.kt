package com.example.aplikasisiswa.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplikasisiswa.modelData.DataSiswa
import com.example.aplikasisiswa.uicontroller.route.DestinasiDetail
import com.example.aplikasisiswa.uicontroller.route.DestinasiEdit
import com.example.aplikasisiswa.uicontroller.route.DestinasiEntry
import com.example.aplikasisiswa.uicontroller.route.DestinasiHome
import com.example.aplikasisiswa.view.DetailSiswaScreen
import com.example.aplikasisiswa.view.EditSiswaScreen
import com.example.aplikasisiswa.view.EntrySiswaScreen
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
            HomeScreen(navigateToItemEntry = {navController.navigate(DestinasiEntry.route)}, navigateToItemUpdate = {})
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = {navController.navigate(DestinasiHome.route)})
        }
        composable(DestinasiDetail.routeWithArgs, arguments = listOf(navArgument(DestinasiDetail.itemIdArg) {
            type = NavType.IntType
        })) {
            DetailSiswaScreen(navigeteToEditItem = {navController.navigate("${DestinasiEdit.route}/$it")}, navigateBack = { navController.navigate(DestinasiHome.route) })
        }
        composable(DestinasiEdit.routeWithArgs, arguments = listOf(navArgument(DestinasiEdit.itemArg) {
            type = NavType.IntType
        })){
            EditSiswaScreen(navigateBack = { navController.navigate(DestinasiHome.route) },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}