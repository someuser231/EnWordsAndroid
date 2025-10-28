package com.example.enwordsandroid.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Dictionary() {
    val words = listOf<String>("Word1", "Word2", "Word3", "Word4", "Word5")
    Box(
        modifier = Modifier.fillMaxSize().padding(30.dp)
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            items(words) { item ->
                Text(item)
                Spacer(Modifier.height(5.dp))
            }
        }
    }
}