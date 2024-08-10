package com.example.sendmessagefirstdraft.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sendmessagefirstdraft.screens.AddMoreSmsContacts
import com.example.sendmessagefirstdraft.screens.AddMoreWhatsappContact
import com.example.sendmessagefirstdraft.screens.MainScreen
import com.example.sendmessagefirstdraft.screens.SmsScreen
import com.example.sendmessagefirstdraft.screens.WhatsappScreen

enum class Screens {
    MainScreen,
    SmsScreen,
    WhatsappScreen,
    AddSmsContact,
    AddWhatsappContact
}

@Composable
fun NavigationRules(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.name
    ) {

        composable(route = Screens.MainScreen.name , content = {
            MainScreen(navController)
        })

        composable(route = Screens.SmsScreen.name , content = {
            SmsScreen(navController)
        })

        composable(route = Screens.AddSmsContact.name , content = {
            AddMoreSmsContacts(navController)
        })

        composable(route = Screens.WhatsappScreen.name , content = {
            WhatsappScreen(navController)
        })

        composable(route = Screens.AddWhatsappContact.name , content = {
            AddMoreWhatsappContact(navController)
        })
    }
}