package com.example.enwordsandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.enwordsandroid.ui.themes.MainTheme
import com.example.enwordsandroid.ui.themes.clrSecondary
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home() {
    MainTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            UserInput()
            Spacer(Modifier.height(15.dp))
            HorizontalDivider(color = Color.Black)
            Spacer(Modifier.height(15.dp))
            Translation()
        }
    }

}

@Composable
fun UserInput(
    mainViewModel: MainViewModel = koinViewModel()
) {
    Column {
        var inputWord by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputWord,
            onValueChange = {
                inputWord = it
            },
            label = { "Input word" },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = clrSecondary,
                focusedContainerColor = clrSecondary,
            )
        )
        Spacer(Modifier.height(20.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    mainViewModel.translate(inputWord)
                },
            ) {
                Text("Translate")
            }
            Spacer(Modifier.width(100.dp))
            Button(
                onClick = {
                    mainViewModel.saveWord(inputWord)
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
        Column (
            modifier = Modifier.offset(30.dp)
        ) {
            Text(
                text = "us: [" + (mainViewModel.word.collectAsState().value?.tcUs ?: "") + "]"
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = "uk: [" + (mainViewModel.word.collectAsState().value?.tcUk ?: "") + "]"
            )
        }
        Spacer(Modifier.height(20.dp))
        HorizontalDivider(color = Color.Black)

        val wForms = mainViewModel.word.collectAsState().value?.wordForm ?: listOf()
        val tls = mainViewModel.word.collectAsState().value?.tl ?: listOf()
        LazyColumn {
            items(wForms + tls) { text ->
                Text(text = "- $text")
            }
        }
    }
}
