package com.me.livecoding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.me.livecoding.R
import com.me.livecoding.commons.loadPicture
import com.me.livecoding.networking.model.ResponseModel
import com.me.livecoding.presentation.MainViewModel
import org.koin.androidx.compose.getViewModel

const val CANT_IMG = 20
const val DEFAULT_IMAGE = R.mipmap.ic_empty
const val DEFAULT_IMAGE_URL = "https://api.chucknorris.io/img/chucknorris_logo_coloured_small@2x.png"

@Composable
fun ShowMainScreen(viewModel: MainViewModel = getViewModel()) {
    var myjokes by remember {
        mutableStateOf(listOf<ResponseModel>(), policy = neverEqualPolicy())
    }

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        if (myjokes.isEmpty()) {
            viewModel.retrieveJokes(CANT_IMG)
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp),
                onClick = {
                    myjokes = viewModel.jokes
                }
            ) {
                Text(text = "¿Bromeamos?, haz Click")
            }
        }
        ShowJokes(items = myjokes)
    }

}

@Composable
fun ShowJokes(items : List<ResponseModel>) {
    LazyColumn (modifier = Modifier.fillMaxSize()) {
        // IMPORTANTE: las imagenes obtenidas del API RESTful estan presentando el siguiente issue:
        // imgUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png"
        // Error: The image "[imgUrl]" cannot be displayed because it contains errors
//        items(items) { item -> ListItem(imgUrl = item.icon_url, value = item.value, url = item.url )}
        //TODO esta linea hace la carga de una imagen que si esta en buen estado para demostrar que la carga remota esta funcional
        items(items) { item -> ListItem(imgUrl = DEFAULT_IMAGE_URL, value = item.value, url = item.url )}
    }
}

@Composable
fun ListItem(imgUrl: String, value: String, url: String = "") {
    Column (
    ) {
        val image = loadPicture(url = imgUrl, defaultImage = DEFAULT_IMAGE)
        if (image.value != null) {
            Image(
                bitmap = image.value!!.asImageBitmap(),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            text = value
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
