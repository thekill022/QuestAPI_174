package com.example.aplikasisiswa.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.aplikasisiswa.viewmodel.DetailViewModel
import com.example.aplikasisiswa.viewmodel.provider.PenyediaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasisiswa.R
import com.example.aplikasisiswa.modelData.DataSiswa
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

@Composable
fun BodyDetailDataSiswa(
    statusUIDetail: StatusUIDetail,
    onDelete : () -> Unit,
    modifier : Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable {
            mutableStateOf(false)
        }
        when(statusUIDetail) {
            is StatusUIDetail.Success -> DetailDataSiswa(siswa = statusUIDetail.satuSiswa, modifier = Modifier.fillMaxWidth())
            else -> {}
        }
        OutlinedButton(
            onClick = {
                deleteConfirmationRequired = true
            },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = stringResource(id = R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = {
                    deleteConfirmationRequired = false
                },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }

}

@Composable
fun DetailDataSiswa(
    siswa : DataSiswa, modifier : Modifier = Modifier
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {

            BarisDetailData(
                labelResID = R.string.nama1,
                itemDetail = siswa.nama,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                ))
            BarisDetailData(
                labelResID = R.string.alamat1,
                itemDetail = siswa.alamat,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                ))
            BarisDetailData(
                labelResID = R.string.telpon1,
                itemDetail = siswa.telpon,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                ))
        }
    }

}

@Composable
private fun BarisDetailData(
    @StringRes labelResID : Int, itemDetail : String, modifier : Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        stringResource(id = labelResID)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm : () -> Unit,
    onDeleteCancel : () -> Unit,
    modifier : Modifier = Modifier
) {

    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(id = R.string.attention))},
        text = { Text(text = stringResource(id = R.string.tanya))},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        }
        )

}