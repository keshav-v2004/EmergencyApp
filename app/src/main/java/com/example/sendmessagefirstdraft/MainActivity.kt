package com.example.sendmessagefirstdraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.sendmessagefirstdraft.navigation.NavigationRules
import com.example.sendmessagefirstdraft.screens.MainScreenViewModel
import com.example.sendmessagefirstdraft.ui.theme.SendMessageFirstDraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            SendMessageFirstDraftTheme {
                NavigationRules(viewModel)
            }
        }
    }
}