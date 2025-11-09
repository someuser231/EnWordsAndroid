package com.example.enwordsandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.enwordsandroid.R
import com.example.enwordsandroid.ui.themes.clrLearnStatus0
import com.example.enwordsandroid.ui.themes.clrLearnStatus1
import com.example.enwordsandroid.ui.themes.clrLearnStatus2
import com.example.enwordsandroid.ui.themes.clrLearnStatus3
import com.example.enwordsandroid.ui.themes.clrSelect
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Dictionary(
    mainViewModel: MainViewModel = koinViewModel(),
    toAddlInfo: (id: Int) -> Unit
) {
    LaunchedEffect(Unit) {
        mainViewModel.getWords()
    }

    val words = mainViewModel.dbWords.collectAsState().value?.toMutableStateList()
        ?: mutableListOf()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            items(words) { item ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            toAddlInfo(item.id!!)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_circle),
                            contentDescription = "Learning status",
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(
                                when(item.learningStatus) {
                                    0 -> clrLearnStatus0
                                    1 -> clrLearnStatus1
                                    2 -> clrLearnStatus2
                                    else -> clrLearnStatus3
                                }
                            )
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            modifier = Modifier.background(
                                if (item.inLearning) clrSelect else Color.Transparent
                            ),
                            text = item.word,
                        )
                    }

                    Row {
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
                        Spacer(Modifier.width(5.dp))
                        Button(
                            onClick = {
                                mainViewModel.deleteWord(item)
                                words.remove(item)
                            }
                        ) {
                            Text("Delete")
                        }
                    }

                }
                Spacer(Modifier.height(2.dp))
                HorizontalDivider(color = Color(0, 0, 0, 50))
                Spacer(Modifier.height(6.dp))
            }
        }
    }
}