package com.example.aplikasisiswa.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aplikasisiswa.modelData.DataSiswa

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(),
                 modifier : Modifier) {
    HostNavigasi(navController = navController)
}