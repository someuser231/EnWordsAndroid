package com.example.enwordsandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.enwordsandroid.ui.Drawer
import com.example.enwordsandroid.ui.Home
import com.example.enwordsandroid.view_models.MainVM

class MainActivity : ComponentActivity() {
    val mainVm: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Drawer(mainVm)
        }
    }
}



