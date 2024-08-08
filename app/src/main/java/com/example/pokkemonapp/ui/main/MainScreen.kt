package com.example.pokkemonapp.ui.main


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokkemonapp.data.viewmodel.MainViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.pokkemonapp.R
import com.example.pokkemonapp.data.model.Pokemon

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList()) // Use observeAsState for LiveData
    Scaffold() {
        LazyColumn {
                item {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Image(
                            painter = painterResource(id = R.drawable.pokemon),
                            contentDescription = null,
                            modifier = Modifier
                                .width(300.dp)
                                .height(200.dp)
                                .align(Alignment.CenterHorizontally),
                            contentScale = ContentScale.Crop
                        )

                    }
                }

            items(pokemonList) { pokemon ->
                PokemonItem(pokemon) {
                    val pokemonId = extractIdFromUrl(pokemon.url)
                    Log.d("PokemonId", "Navigating to detail screen with ID: $pokemonId")
                    navController.navigate("detail/$pokemonId")
                }
            }            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)// Set the height for the Box
            )
        }
    }

fun extractIdFromUrl(url: String): Int? {
    val regex = Regex("/pokemon/(\\d+)/")
    val match = regex.find(url)
    return match?.groups?.get(1)?.value?.toIntOrNull()
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Set the background color here
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp) // Margin between each card item
            .clickable { onClick() }
            .border(BorderStroke(2.dp, Color(0xFFFE389B)))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(100.dp)
                .padding(16.dp)   .background(Color.Transparent)
        ) {
            val formattedName = pokemon.name.replaceFirstChar { it.titlecase() }

            Text(formattedName, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.align(Alignment.Center) )
        }
    }
}
