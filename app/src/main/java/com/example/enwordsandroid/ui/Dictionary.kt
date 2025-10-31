package com.example.enwordsandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enwordsandroid.view_models.MainViewModel

@Composable
fun Dictionary(mainViewModel: MainViewModel) {
    val words = mainViewModel.dbWords.collectAsState().value ?: listOf()
    Box(
        modifier = Modifier.fillMaxSize().padding(30.dp)
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            items(words) { item ->
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.word)
                    Text(item.inLearning.toString())
                    Button(
                        onClick = {
                            item.inLearning = !item.inLearning
                            mainViewModel.updateWord(item)
                        }
                    ) {
                        Text("Learn")
                    }
                    Button(
                        onClick = {
                            mainViewModel.deleteWord(item)
                        }
                    ) {
                        Text("Delete")
                    }
                }
                Spacer(Modifier.height(5.dp))
            }
        }
    }
}