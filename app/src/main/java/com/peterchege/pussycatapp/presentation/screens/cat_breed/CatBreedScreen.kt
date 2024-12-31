/*
 * Copyright 2023 Pussy Cat App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.pussycatapp.presentation.screens.cat_breed

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.peterchege.pussycatapp.core.util.getCatImageById
import org.koin.androidx.compose.koinViewModel


@Composable
fun CatBreedScreen(
    navController:NavController,
    breedId:String,
){
    val viewModel = koinViewModel<CatBreedScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = breedId){
        viewModel.getCatBreedById(breedId = breedId)
    }

    CatBreedScreenContent(
        navController = navController,
        uiState = uiState,
        retryCallBack = { viewModel.getCatBreedById(breedId) }
    )



}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreenContent(
    navController:NavController,
    uiState:CatBreedScreenUiState,
    retryCallBack:() -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            when(uiState){
                is CatBreedScreenUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),

                        ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                is CatBreedScreenUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = uiState.message)
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                retryCallBack()
                            }
                        ) {
                            Text(text = "Retry")

                        }
                    }
                }
                is CatBreedScreenUiState.Success -> {
                    val catBreed = uiState.catBreed
                    var offset by remember { mutableStateOf(Offset.Zero) }
                    var zoom by remember { mutableStateOf(1f) }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        SubcomposeAsyncImage(
                            model = getCatImageById(imageId = catBreed.reference_image_id),
                            loading = {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(
                                            Alignment.Center
                                        )
                                    )
                                }
                            },
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clipToBounds()
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onDoubleTap = { tapOffset ->
                                            zoom = if (zoom > 1f) 1f else 2f
                                            offset = calculateDoubleTapOffset(
                                                zoom,
                                                size,
                                                tapOffset
                                            )

                                        }
                                    )
                                }
                                .pointerInput(Unit) {
                                    detectTransformGestures(
                                        onGesture = { centroid, pan, gestureZoom, _ ->
                                            offset = offset.calculateNewOffset(
                                                centroid, pan, zoom, gestureZoom, size
                                            )
                                            zoom = maxOf(a = 1f, b = zoom * gestureZoom)
                                        }
                                    )
                                }
                                .graphicsLayer {
                                    translationX = -offset.x * zoom
                                    translationY = -offset.y * zoom
                                    scaleX = zoom
                                    scaleY = zoom
                                    transformOrigin = TransformOrigin(
                                        pivotFractionX = 0f,
                                        pivotFractionY = 0f
                                    )
                                }
                            ,
                            contentDescription = "Cat Images"
                        )
                        Text(text = "Cat breed : ${catBreed.name}")
                        Text(text = "Description : ${catBreed.description}")
                        Text(text = "Origin : ${catBreed.origin}")

                    }


                }
            }


        }

    }

}

fun Offset.calculateNewOffset(
    centroid: Offset,
    pan: Offset,
    zoom: Float,
    gestureZoom: Float,
    size: IntSize
): Offset {
    val newScale = maxOf(1f, zoom * gestureZoom)
    val newOffset = (this + centroid / zoom) -
            (centroid / newScale + pan / zoom)
    return Offset(
        newOffset.x.coerceIn(0f, (size.width / zoom) * (zoom - 1f)),
        newOffset.y.coerceIn(0f, (size.height / zoom) * (zoom - 1f))
    )
}

fun calculateDoubleTapOffset(
    zoom: Float,
    size: IntSize,
    tapOffset: Offset
): Offset {
    val newOffset = Offset(tapOffset.x, tapOffset.y)
    return Offset(
        newOffset.x.coerceIn(0f, (size.width / zoom) * (zoom - 1f)),
        newOffset.y.coerceIn(0f, (size.height / zoom) * (zoom - 1f))
    )
}