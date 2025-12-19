package com.example.aplikasisiswa.uicontroller.route

import androidx.annotation.StringRes

interface DestinasiNavigasi {
    val route : String
    @get:StringRes
    val titleRes : Int
}