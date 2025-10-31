package com.example.enwordsandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.WordModel
import com.example.enwordsandroid.view_models.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow

val lnWord = MutableStateFlow<WordModel?>(null)

@Composable
fun Learning(mainViewModel: MainViewModel) {
    Column (
        modifier = Modifier.fillMaxSize().padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = lnWord.collectAsState().value?.word ?: "A word will be here",
            fontSize = 20.sp,
        )
        Spacer(Modifier.height(100.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    lnWord.value = mainViewModel.learnWords(true)
                }
            ) {
                Text("I know it")
            }

            Button(
                onClick = {
                    lnWord.value = mainViewModel.learnWords(false)
                }
            ) {
                Text("I don't know it")
            }
        }

    }
}