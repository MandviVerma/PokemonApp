package com.example.pokkemonapp.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
                                .weight(1f)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                RemoteImage(
                                    url = it.sprites.front_default,
                                    height = 300.dp,
                                    width = 300.dp,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Image description",
                                    loadingView = {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator() // Loader
                                        }
                                    },
                                    errorView = {
                                        Text("Failed to load image")
                                    }
                                )

                                Text(
                                    text = it.name.uppercase(),
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFE389B),
                                    fontSize = 30.sp,
                                    modifier = Modifier
                                        .padding(top = 4.dp)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Height - ${it.height}",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(top = 4.dp)
                                )
                                Text(
                                    text = "Weight - ${it.weight}",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    modifier = Modifier
                                        .padding(top = 4.dp)
                                )
                            }

                        }
                    }
                }
            }
        }
 }




