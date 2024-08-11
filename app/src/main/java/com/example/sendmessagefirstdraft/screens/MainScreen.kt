package com.example.sendmessagefirstdraft.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sendmessagefirstdraft.R
import com.example.sendmessagefirstdraft.navigation.Screens
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navController: NavController,

) {

    val snackbarHost = remember{ SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHost) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            Logos(viewModel, navController, snackbarHost)
            BelowLogos()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Logos(
    viewModel: MainScreenViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

    ) {

        Image(
            painter = painterResource(id = R.drawable._6zvtnln3toz309wttldiwp_25),
            contentDescription = null,
            modifier = Modifier
                .combinedClickable(
                    onClick = { navController.navigate(Screens.WhatsappScreen.name) },
                    onLongClick = {
                        viewModel.updateDialogState(
                            viewModel.showWhatsappDialog,
                            Screens.WhatsappScreen.name
                        )
                    }
                )
        )


        Image(painter = painterResource(
            id = R.drawable.white_exclamation_mark_sign_red_circle_isolated_white_background_120819_332
        ),
            contentDescription = null,
            modifier = Modifier
                .combinedClickable(
                    onClick = { navController.navigate(Screens.SmsScreen.name) },
                    onLongClick = {
                        viewModel.updateDialogState(viewModel.showSmsDialog, Screens.SmsScreen.name)
                    }
                )
        )

    }



    if (viewModel.showSmsDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.updateDialogState(viewModel.showSmsDialog , Screens.SmsScreen.name) },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("sending")
                        }
                    }
                ) {
                    Text("confirm")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { viewModel.updateDialogState(viewModel.showSmsDialog , Screens.SmsScreen.name) }
                ) {
                    Text(text = "Abort")
                }
            },
            title = { Text(text = "Warning!") },
            text = { Text(text = "you are about to perform a critical task.Please confirm to proceed")}
        )
    }

    else if (viewModel.showWhatsappDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.updateDialogState(viewModel.showWhatsappDialog , Screens.WhatsappScreen.name) },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("sending")
                        }
                    }
                        
                ) {
                    Text("confirm")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { viewModel.updateDialogState(viewModel.showWhatsappDialog , Screens.WhatsappScreen.name) }
                ) {
                    Text(text = "Abort")
                }
            },
            title = { Text(text = "Warning!") },
            text = { Text(text = "you are about to perform a critical task.Please confirm to proceed")}
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
                .clickable { }
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
                .clickable { }
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
//    MainScreen()
}