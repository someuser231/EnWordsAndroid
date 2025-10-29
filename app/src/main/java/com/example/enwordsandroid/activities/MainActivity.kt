package com.example.enwordsandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.enwordsandroid.ui.Drawer
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        mainViewModel.repo
        setContent {
            Drawer(mainViewModel)
        }
    }
}



