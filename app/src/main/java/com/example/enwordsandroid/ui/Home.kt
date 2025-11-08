package com.example.enwordsandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.enwordsandroid.ui.themes.MainTheme
import com.example.enwordsandroid.view_models.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel


var inputWord = MutableStateFlow("")

@Preview(showBackground = true)
@Composable
fun Home() {
    MainTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            UserInput()
            Spacer(Modifier.height(10.dp))
            Translation()
        }
    }

}

@Composable
fun UserInput(
    mainViewModel: MainViewModel = koinViewModel()
) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputWord.collectAsState().value,
            onValueChange = {
                inputWord.value = it
            },
            label = { "Input word" }
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    mainViewModel.translate(inputWord.value)
                },
            ) {
                Text("Translate")
            }
            Button(
                onClick = {
                    mainViewModel.saveWord()
                },
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun Translation(
    mainViewModel: MainViewModel = koinViewModel()
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = mainViewModel.word.collectAsState().value?.tcUs ?: "us"
            )
            Spacer(Modifier.Companion.width(20.dp))
            Text(
                text = mainViewModel.word.collectAsState().value?.tcUk ?: "uk"
            )
        }
        Spacer(Modifier.height(20.dp))
        val tls = mainViewModel.word.collectAsState().value?.tl ?: listOf("")
        LazyColumn {
            items(tls) { tl ->
                Text(text = "- $tl")
            }
        }
    }

}