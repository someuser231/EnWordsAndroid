package com.example.enwordsandroid.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enwordsandroid.view_models.MainVM
import kotlinx.coroutines.flow.MutableStateFlow

var inputWord = MutableStateFlow("")

@Composable
fun Home(mainVM: MainVM) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        UserInput(mainVM)
        Spacer(Modifier.height(10.dp))
        Translation(mainVM)
    }
}

@Composable
fun UserInput(mainVM: MainVM) {
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
                onClick = { mainVM.translate(inputWord.value) },
            ) {
                Text("Translate")
            }
            Button(
                onClick = { },
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun Translation(mainVm: MainVM) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = mainVm.word.collectAsState().value?.tcUs ?: "us"
            )
            Spacer(Modifier.Companion.width(20.dp))
            Text(
                text = mainVm.word.collectAsState().value?.tcUk ?: "uk"
            )
        }
        Spacer(Modifier.height(20.dp))
        val tls = mainVm.word.collectAsState().value?.tl ?: listOf("")
        LazyColumn {
            items(tls) { tl ->
                Text(text = "- $tl")
            }
        }
    }

}