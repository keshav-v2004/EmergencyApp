package com.example.sendmessagefirstdraft

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SubscriptionManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.example.sendmessagefirstdraft.navigation.NavigationRules
import com.example.sendmessagefirstdraft.screens.MainScreenViewModel
import com.example.sendmessagefirstdraft.screens.SmsScreenViewModel
import com.example.sendmessagefirstdraft.screens.WhatsappScreenViewModel
import com.example.sendmessagefirstdraft.ui.theme.SendMessageFirstDraftTheme
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]

        val smsScreenViewModel = ViewModelProvider(this)[SmsScreenViewModel::class.java]

        val whatsappScreenViewModel = ViewModelProvider(this)[WhatsappScreenViewModel::class.java]

        val smsManager  =
            this.getSystemService<SmsManager>(SmsManager::class.java)
                .createForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId())

//        val smsManager : SmsManager = SmsManager.getSmsManagerForSubscriptionId()


//        Context. getSystemService(SmsManager. class) .createForSubscriptionId(subId)

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        enableEdgeToEdge()
        setContent {
            SendMessageFirstDraftTheme {
                NavigationRules(
                    viewModel ,
                    smsScreenViewModel ,
                    whatsappScreenViewModel ,
                    smsManager,
                    fusedLocationClient
                )
            }
        }
    }
}