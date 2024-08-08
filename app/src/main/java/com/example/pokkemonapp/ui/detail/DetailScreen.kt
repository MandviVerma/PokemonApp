package com.example.pokkemonapp.ui.detail

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokkemonapp.data.viewmodel.DetailViewModel

@Composable
fun DetailScreen(pokemonId: Int, viewModel: DetailViewModel = hiltViewModel()) {
    val pokemonDetail by viewModel.pokemonDetail.observeAsState()

    pokemonDetail?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(it.name, style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp))
            Text("Height: ${it.height}")
            Text("Weight: ${it.weight}")
        }
    }
}
