package com.example.sendmessagefirstdraft.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sendmessagefirstdraft.navigation.Screens

class MainScreenViewModel : ViewModel() {

    var showWhatsappDialog by mutableStateOf(false)
        private set

    var showSmsDialog by mutableStateOf(false)
        private set

    fun updateDialogState(show : Boolean , whichToUpdate : String) {

        Log.i("screen is ${whichToUpdate} and its value is " , "$show")
        if (whichToUpdate == Screens.SmsScreen.name) {

            Log.i("screen is and its value is " , "${whichToUpdate}+$show")
            showSmsDialog = !showSmsDialog
        }
        else{

            Log.i("screen is and its value is " , "${whichToUpdate}+$show")
            showWhatsappDialog = !showWhatsappDialog
        }
    }
}