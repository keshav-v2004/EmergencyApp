package com.example.sendmessagefirstdraft.navigation

import android.telephony.SmsManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sendmessagefirstdraft.screens.AddMoreSmsContacts
import com.example.sendmessagefirstdraft.screens.AddMoreWhatsappContact
import com.example.sendmessagefirstdraft.screens.MainScreen
import com.example.sendmessagefirstdraft.screens.MainScreenViewModel
import com.example.sendmessagefirstdraft.screens.SmsScreen
import com.example.sendmessagefirstdraft.screens.SmsScreenViewModel
import com.example.sendmessagefirstdraft.screens.WhatsappScreen
import com.example.sendmessagefirstdraft.screens.WhatsappScreenViewModel
import com.google.android.gms.location.FusedLocationProviderClient

enum class Screens {
    MainScreen,
    SmsScreen,
    WhatsappScreen,
    AddSmsContact,
    AddWhatsappContact
}

@Composable
fun NavigationRules(
    viewModel: MainScreenViewModel,
    smsScreenViewModel: SmsScreenViewModel,
    whatsappScreenViewModel: WhatsappScreenViewModel,
    smsManager: SmsManager,
    fusedLocationClient : FusedLocationProviderClient,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.name
    ) {

        composable(route = Screens.MainScreen.name , content = {
            MainScreen(
                viewModel,navController , smsManager , smsScreenViewModel , whatsappScreenViewModel , fusedLocationClient)
        })

        composable(route = Screens.SmsScreen.name , content = {
            SmsScreen( smsScreenViewModel , navController)
        })

        composable(route = Screens.AddSmsContact.name , content = {
            AddMoreSmsContacts(smsScreenViewModel,navController)
        })

        composable(route = Screens.WhatsappScreen.name , content = {
            WhatsappScreen(navController , whatsappScreenViewModel)
        })

        composable(route = Screens.AddWhatsappContact.name , content = {
            AddMoreWhatsappContact(navController , whatsappScreenViewModel)
        })
    }
}