package com.example.pokkemonapp.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


@Composable
fun RemoteImage(
    url: String,
    height: Dp,
    width: Dp,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onLoadSuccess: () -> Unit = {},
    onLoadFailed: () -> Unit = {},
    onNewRequest: () -> Unit = {},
    loadingView: @Composable (() -> Unit)? = null,
    errorView: @Composable (() -> Unit)? = null,
    transformation: Transformation<Bitmap>? = null, // todo: Support multiple transformations
    colorFilter: ColorFilter? = null,
    adjustToFillBounds: Boolean = false
) {
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    val isLoading = remember { mutableStateOf(true) }
    val didFail = remember { mutableStateOf(false) }

    val heightPixel = LocalDensity.current.run { height.toPx() }.toInt()
    val widthPixel = LocalDensity.current.run { width.toPx() }.toInt()

    val glide = Glide.with(LocalContext.current.applicationContext)

    DisposableEffect(url) {
        bitmap.value = null
        onNewRequest()
        val target = object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmap.value = resource
                isLoading.value = false
                onLoadSuccess()
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                didFail.value = true
                isLoading.value = false
                onLoadFailed()
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        }
        val request = glide
            .asBitmap()
            .load(url)
            .apply(RequestOptions().override(widthPixel, heightPixel));

        if (transformation != null) {
            request.transform(transformation)
        }
        request.into(target)

        onDispose {
            glide.clear(target)
        }
    }

    Box(modifier = modifier.width(width).height(height)) {
        if (bitmap.value != null && !isLoading.value) {
            val imageModifier = Modifier
                .width(width)
                .height(height)
            if (adjustToFillBounds) {
                imageModifier.fillMaxSize()
            }

            Image(
                modifier = imageModifier,
                bitmap = bitmap.value!!.asImageBitmap(),
                contentDescription = contentDescription,
                contentScale = contentScale,
                colorFilter = colorFilter
            )
        } else if (isLoading.value && loadingView != null) {
            loadingView()
        } else if(didFail.value && errorView != null) {
            errorView()
        }
    }
}