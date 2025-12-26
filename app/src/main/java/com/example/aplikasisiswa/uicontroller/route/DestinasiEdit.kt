package com.example.aplikasisiswa.uicontroller.route

import com.example.aplikasisiswa.R

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemArg = "idSiswa"
    val routeWithArgs = "$route/{$itemArg}"
}