package com.example.enwordsandroid.ui

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.enwordsandroid.ui.themes.MainTheme
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddlWordInfo(
    mainViewModel: MainViewModel = koinViewModel(),
    navController: NavController,
    wordId: Int
) {
    LaunchedEffect(Unit) {
        mainViewModel.getOneWord(wordId)
    }
    MainTheme {
        Column (
            modifier = Modifier.fillMaxSize().padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back")
                }
            }
            Text(mainViewModel.word.collectAsState().value?.word ?: "Word")
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(mainViewModel.word.collectAsState().value?.tcUs ?: "Us")
                Spacer(Modifier.width(100.dp))
                Text(mainViewModel.word.collectAsState().value?.tcUk ?: "Uk")
            }
            Spacer(Modifier.height(20.dp))

            val wForms = mainViewModel.word.collectAsState().value?.wordForm ?: listOf()
            val tls = mainViewModel.word.collectAsState().value?.tl ?: listOf()
            LazyColumn {
                items(wForms + tls) { tl ->
                    Text(text = "- $tl")
                }
            }
        }
    }

}