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
import com.example.sendmessagefirstdraft.R

@Composable
fun WhatsappScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }
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
                .background(Color(0xff25D366))
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable._6zvtnln3toz309wttldiwp_25),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "all saved whatsapp enabled phone numbers will be stored here",
                modifier = modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun AddMoreWhatsappContact(
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
            .background(Color(0xff25D366))
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable._6zvtnln3toz309wttldiwp_25),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = number,
            onValueChange = {number = it},
            placeholder = { Text(text = "enter whatsapp enabled number")}
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            placeholder = { Text(text = "enter name")}
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
            Text(text = "save whatsapp contact")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewFxn() {
    WhatsappScreen()

}