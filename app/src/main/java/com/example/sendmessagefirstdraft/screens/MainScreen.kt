package com.example.sendmessagefirstdraft.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sendmessagefirstdraft.R

@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Logos()

        BelowLogos()
    }
}

@Composable
fun Logos() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

    ) {

        Image(
            painter = painterResource(id = R.drawable._6zvtnln3toz309wttldiwp_25),
            contentDescription = null
        )

        Image(painter = painterResource(
            id = R.drawable.white_exclamation_mark_sign_red_circle_isolated_white_background_120819_332),
            contentDescription = null
        )
    }
}

@Composable
fun BelowLogos(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Card(
            modifier = Modifier
                .weight(1f)
        ) {
            Box {
                Text(
                    text = "Medical information",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Card (
            modifier = Modifier
                .weight(1f)
        ){
            Box {
                Text(
                    text = "Call Emergency",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewFxn() {
    MainScreen()
}