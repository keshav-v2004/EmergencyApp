package com.example.sendmessagefirstdraft.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sendmessagefirstdraft.R
import com.example.sendmessagefirstdraft.navigation.Screens

@Composable
fun SmsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddSmsContact.name) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xffed4545))
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.white_exclamation_mark_sign_red_circle_isolated_white_background_120819_332), 
                contentDescription = null)
            
            Text(text = "please be careful while using this")

            Text(text = "(up to 6 contacts only)")
            
            Text(text = "all saved contacts will be shown here")
        }
    }

}

@Composable
fun AddMoreSmsContacts(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var number by rememberSaveable {
        mutableStateOf("")
    }

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var message by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(Color(0xffed4545))
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.white_exclamation_mark_sign_red_circle_isolated_white_background_120819_332),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = number,
            onValueChange = {number = it},
            placeholder = { Text(text = "enter receiver number")}
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            placeholder = { Text(text = "enter receiver name")}
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = message,
            onValueChange = {message = it},
            placeholder = { Text(text = "enter message")}
        )

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "save contact")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewFxn() {

//    AddMoreSmsContacts()
}
