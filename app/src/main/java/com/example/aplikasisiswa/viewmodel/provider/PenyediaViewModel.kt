package com.example.aplikasisiswa.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.aplikasisiswa.repositori.AplikasiDataSiswa
import com.example.aplikasisiswa.viewmodel.DetailViewModel
import com.example.aplikasisiswa.viewmodel.EditViewModel
import com.example.aplikasisiswa.viewmodel.EntryViewModel
import com.example.aplikasisiswa.viewmodel.HomeViewModel

fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiDataSiswa
        )
object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            val aplikasi = aplikasiDataSiswa()
            HomeViewModel(aplikasi.container.repositoryDataSiswa)
        }
        initializer {
            val aplikasi = aplikasiDataSiswa()
            EntryViewModel(aplikasi.container.repositoryDataSiswa)
        }
        initializer {
            val aplikasi = aplikasiDataSiswa()
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasi.container.repositoryDataSiswa)
        }
        initializer {
            val aplikasi = aplikasiDataSiswa()
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasi.container.repositoryDataSiswa
            )
        }
    }
}