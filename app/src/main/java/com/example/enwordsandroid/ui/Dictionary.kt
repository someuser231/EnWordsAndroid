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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Dictionary(
    mainViewModel: MainViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        mainViewModel.getWords()
    }

    val words = mainViewModel.dbWords.collectAsState().value?.toMutableStateList() ?: mutableListOf()

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
                            val newItem = item.copy()
                            newItem.inLearning = !newItem.inLearning
                            mainViewModel.updateWord(newItem)
                            for (i in words.indices) {
                                if (words[i].id == item.id) words[i] = newItem
                            }
                        }
                    ) {
                        Text("Learn")
                    }
                    Button(
                        onClick = {
                            mainViewModel.deleteWord(item)
                            words.remove(item)
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