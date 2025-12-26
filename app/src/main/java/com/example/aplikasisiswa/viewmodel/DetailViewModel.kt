package com.example.aplikasisiswa.viewmodel

import com.example.aplikasisiswa.modelData.DataSiswa

sealed interface StatusUIDetail {
 data class Success(val satuSiswa : DataSiswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}