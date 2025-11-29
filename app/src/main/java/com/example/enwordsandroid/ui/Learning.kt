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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.WordModel
import com.example.enwordsandroid.view_models.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun Learning(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val lnWord = remember {
        MutableStateFlow<WordModel?>(null)
    }

    LaunchedEffect(Unit) {
        mainViewModel.getLearnWords()
        lnWord.value = mainViewModel.learnWords(lnWord.value, false)
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text =
                lnWord.collectAsState().value?.word ?:
                mainViewModel.lnWords.collectAsState().value?.randomOrNull()?.word ?:
                "Select words for learning...",
            fontSize = 20.sp,
        )
        Spacer(Modifier.height(100.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    lnWord.value = mainViewModel.learnWords(lnWord.value, true)
                }
            ) {
                Text("I know it")
            }

            Button(
                onClick = {
                    lnWord.value = mainViewModel.learnWords(lnWord.value, false)
                }
            ) {
                Text("I don't know it")
            }
        }

    }
}