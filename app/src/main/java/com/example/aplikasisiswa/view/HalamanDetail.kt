package com.example.aplikasisiswa.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.aplikasisiswa.viewmodel.DetailViewModel
import com.example.aplikasisiswa.viewmodel.provider.PenyediaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasisiswa.R
import com.example.aplikasisiswa.uicontroller.route.DestinasiDetail
import com.example.aplikasisiswa.viewmodel.StatusUIDetail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigeteToEditItem : (Int) -> Unit,
    navigateBack : () -> Unit,
    modifier : Modifier = Modifier,
    viewModel : DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {

    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(id = DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack)
        },
        floatingActionButton = {
            val uiState = viewModel.statusUIDetail
            FloatingActionButton(onClick = {
                when(uiState) {
                    is StatusUIDetail.Success -> navigeteToEditItem(uiState.satuSiswa.id) else -> {}
                }
            },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = stringResource(id = R.string.update))
            }
        },
        modifier = modifier
    ) {innerPadding ->
        val coroutineScope = rememberCoroutineScope()
        BodyDetailDataSiswa(
            statusUIDetail = viewModel.statusUIDetail,
            onDelete = {
                coroutineScope.launch {
                    viewModel.hapusSatuSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()))
    }

}
