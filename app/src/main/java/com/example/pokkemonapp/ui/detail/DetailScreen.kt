package com.example.pokkemonapp.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokkemonapp.R
import com.example.pokkemonapp.data.viewmodel.DetailViewModel
import com.example.pokkemonapp.ui.main.PokemonItem
import com.example.pokkemonapp.util.RemoteImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(pokemonId: Int, viewModel: DetailViewModel = hiltViewModel()) {
    val pokemonDetail by viewModel.pokemonDetail.observeAsState()


        Scaffold {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                pokemonDetail?.let {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Top half
                        Box(
                            modifier = Modifier
                                .weight(1f) // Takes up half of the available space
                                .fillMaxWidth()
                                .background(Color.Transparent) // Ensure background is transparent to show image
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center) // Center the Column horizontally and vertically
                                    .fillMaxWidth(), // Ensure the Column fills the width
                                horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally within the Column
                                verticalArrangement = Arrangement.Center // Center content vertically within the Column
                            ) {
                                RemoteImage(
                                    url = it.sprites.front_default,
                                    height = 300.dp, // Set the desired height
                                    width = 300.dp, // Set the desired width
                                    contentScale = ContentScale.Crop, // Adjust the content scale as needed
                                    contentDescription = "Image description",
                                    loadingView = {
                                        // Optionally show a loading view while the image is being fetched
                                        CircularProgressIndicator()
                                    },
                                    errorView = {
                                        // Optionally show an error view if loading the image fails
                                        Text("Failed to load image")
                                    }
                                )

                                // Directly below the image with minimal space
                                Text(
                                    text = it.name.uppercase(),
                                    fontWeight = FontWeight.Bold, // Make the text bold
                                    color = Color(0xFFFE389B), // Adjust color as needed
                                    fontSize = 30.sp, // Adjust font size as needed
                                    modifier = Modifier
                                        .padding(top = 4.dp) // Reduced padding to minimize space between image and text
                                )
                            }
                        }




                        // Bottom half
                        Box(
                            modifier = Modifier
                                .weight(1f) // Takes up the remaining half of the available space
                                .fillMaxWidth()
                                .background(Color.Transparent) // Ensure background is transparent to show image
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center) // Center the Column horizontally and vertically
                                    .fillMaxWidth(), // Ensure the Column fills the width
                                horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally within the Column
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Height - ${it.height}",
                                    fontWeight = FontWeight.Bold, // Make the text bold
                                    color = Color.White, // Adjust color as needed
                                    fontSize = 26.sp, // Adjust font size as needed
                                    modifier = Modifier
                                        .padding(top = 4.dp) // Reduced padding to minimize space between image and text
                                )
                                Text(
                                    text = "Weight - ${it.weight}",
                                    fontWeight = FontWeight.Bold, // Make the text bold
                                    color = Color.White, // Adjust color as needed
                                    fontSize = 26.sp, // Adjust font size as needed
                                    modifier = Modifier
                                        .padding(top = 4.dp) // Reduced padding to minimize space between image and text
                                )
                            }

                        }
                    }
                }
            }
        }

//        Scaffold() {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Transparent) // Ensure background is transparent to show image
//            ) {
//            // Background image
//            Image(
//                painter = painterResource(id = R.drawable.bg),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxSize(), // Fill the entire screen
//                contentScale = ContentScale.Crop // Crop to fill the screen while preserving aspect ratio
//            )
//
//            // Centered text
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp), // Optional padding
//                contentAlignment = Alignment.Center // Center the text
//            ) {
//                pokemonDetail?.let {
//                    Column(modifier = Modifier.padding(16.dp))
//                    {  Box(
//                        modifier = Modifier
//                            .weight(1f) // Takes half of the available height
//                            .fillMaxWidth()
//                    )
//
//                        RemoteImage(
//                            url = it.sprites.front_default,
//                            height = 200.dp, // Set the desired height
//                            width = 200.dp, // Use fillMaxWidth to make the image take up the full width
//                            contentScale = ContentScale.Crop, // Adjust the content scale as needed
//                            contentDescription = "Image description",
//                            loadingView = {
//                                // Optionally show a loading view while the image is being fetched
//                                CircularProgressIndicator()
//                            },
//                            errorView = {
//                                // Optionally show an error view if loading the image fails
//                                Text("Failed to load image")
//                            }
//                        )
//                        Box(
//                            modifier = Modifier
//                                .weight(1f) // Takes the remaining half of the available height
//                                .fillMaxWidth()
//                                .padding(16.dp)
//                        ) {
//                            Column {
//                                Text(
//                                    it.name.uppercase(),
//                                    style = MaterialTheme.typography.headlineMedium
//                                )
//                                Text("Height: ${it.height}")
//                                Text("Weight: ${it.weight}")
//                            }
//                        }
//                    }
//                    }
//                }
////                Text(
////                    text = "Your Text Here",
////                    style = MaterialTheme.typography.headlineMedium,
////                    color = Color.White // Change color as needed
////                )
//            }
//        }
        }




