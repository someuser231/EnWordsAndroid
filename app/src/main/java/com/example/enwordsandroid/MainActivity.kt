package com.example.enwordsandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enwordsandroid.view_models.MainVM
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    val mainVm: MainVM by viewModels()
    var txtWord = MutableStateFlow("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment= Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {  }
                ) {
                    Text("Save")
                }
                TextField(
                    value = txtWord.collectAsState().value,
                    onValueChange = {
                        txtWord.value = it
                        mainVm.translate(txtWord.value)
                    },
                )
                Row {
                    Text(
                        text = mainVm.word.collectAsState().value?.tcUs ?: "us transcription"
                    )
                    Spacer(Modifier.width(20.dp))
                    Text(
                        text = mainVm.word.collectAsState().value?.tcUk ?: "uk transcription"
                    )
                }
                Text(
                    text = tlToString(mainVm.word.collectAsState().value?.tl)
                )
            }
        }
    }

    fun tlToString(tl: List<String>?): String {
        var result = ""
        if (tl != null) {
            for (i in tl) {
                result += "- $i\n"
            }
        }
        return result
    }
}