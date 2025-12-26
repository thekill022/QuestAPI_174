package com.example.aplikasisiswa.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.aplikasisiswa.viewmodel.EditViewModel
import com.example.aplikasisiswa.viewmodel.provider.PenyediaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasisiswa.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSiswaScreen(
    navigateBack : () -> Unit,
    onNavigateUp : () -> Unit,
    modifier : Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(id = DestinasiEdit.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val coroutineScope = rememberCoroutineScope()
        EntrySiswaBody(
            uiStateSiswa = viewModel.uiStateSiswa,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editSatuSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
            )

    }
}