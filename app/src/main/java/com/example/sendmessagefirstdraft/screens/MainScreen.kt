package com.example.sendmessagefirstdraft.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sendmessagefirstdraft.R
import com.example.sendmessagefirstdraft.db.SmsList
import com.example.sendmessagefirstdraft.db.WhatsappList
import com.example.sendmessagefirstdraft.navigation.Screens
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch
import java.lang.String

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navController: NavController,
    smsManager: SmsManager,
    smsScreenViewModel: SmsScreenViewModel,
    whatsappScreenViewModel: WhatsappScreenViewModel,
    fusedLocationClient: FusedLocationProviderClient,
) {

    val allSmsContacts by smsScreenViewModel.getSmsContacts().observeAsState()
    val allwhatsappContacts by whatsappScreenViewModel.getAllWhatsappContacts().observeAsState()

    val snackbarHost = remember { SnackbarHostState() }

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
            Logos(
                viewModel,
                navController,
                snackbarHost,
                smsManager, allSmsContacts,
                allwhatsappContacts,
                fusedLocationClient
            )
            BelowLogos(goToMedScreen = { navController.navigate(Screens.MedInfoScreen.name) })

            Spacer(modifier = Modifier.height(35.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Keep calm",
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 35.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Don't panic !",
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 35.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Logos(
    viewModel: MainScreenViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    smsManager: SmsManager,
    allSmsContacts: List<SmsList>?,
    allwhatsappContacts: List<WhatsappList>?,
    fusedLocationClient: FusedLocationProviderClient,
) {

    val scope = rememberCoroutineScope()

    var lati by remember {
        mutableStateOf("")
    }

    var longi by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)

    ) {

        Image(
            painter = painterResource(id = R.drawable._6zvtnln3toz309wttldiwp_25),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
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
                .clip(RoundedCornerShape(50.dp))
                .combinedClickable(
                    onClick = { navController.navigate(Screens.SmsScreen.name) },
                    onLongClick = {
                        viewModel.updateDialogState(viewModel.showSmsDialog, Screens.SmsScreen.name)
                    }
                )
        )

    }


    val context = LocalContext.current


    if (viewModel.showSmsDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.updateDialogState(
                    viewModel.showSmsDialog,
                    Screens.SmsScreen.name
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        try {
                            scope.launch {

                                if (!allSmsContacts.isNullOrEmpty()) {
                                    allSmsContacts.forEach { eachContact ->

                                        try {
                                            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                                                if (location != null) {

                                                    lati = location.latitude.toString()
                                                    longi = location.longitude.toString()

                                                    smsManager.sendTextMessage(
                                                        eachContact.number,
                                                        null,
                                                        eachContact.message + "\nmy coordinates are latitude : $lati , longitude : $longi",
                                                        null, null
                                                    )
                                                }
                                                else {
                                                    smsManager.sendTextMessage(
                                                        eachContact.number,
                                                        null,
                                                        eachContact.message,
                                                        null, null
                                                    )
                                                }
                                            }
                                        } catch (e: Error) {
                                            Toast.makeText(
                                                context,
                                                e.message.toString(),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Sent message to ${allSmsContacts.size} contacts")
                                    }


                                } else {
                                    Toast.makeText(context, "NO contacts added", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        } catch (e: Exception) {
                            Log.i("smsSendingError", e.message.toString())
                        }
                        viewModel.updateDialogState(viewModel.showSmsDialog, Screens.SmsScreen.name)
                    }
                ) {
                    Text("confirm")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        viewModel.updateDialogState(
                            viewModel.showSmsDialog,
                            Screens.SmsScreen.name
                        )
                    }
                ) {
                    Text(text = "Abort")
                }
            },
            title = { Text(text = "Warning!") },
            text = { Text(text = "you are about to perform a critical task.Please confirm to proceed") }
        )
    } else if (viewModel.showWhatsappDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.updateDialogState(
                    viewModel.showWhatsappDialog,
                    Screens.WhatsappScreen.name
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        try {
                            if (!allwhatsappContacts.isNullOrEmpty()) {

                                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                                    if (location != null) {

                                        lati = location.latitude.toString()
                                        longi = location.longitude.toString()

                                        context.startActivity(
                                            Intent(
                                                Intent
                                                    .ACTION_VIEW,
                                                Uri.parse(
                                                    String.format(
                                                        "https://api.whatsapp.com/send?phone=%s&text=%s",
                                                        allwhatsappContacts[0].number,
                                                        allwhatsappContacts[0].message+"\nmy coordinates are\n latitude : $lati\n longitude : $longi"
                                                    )
                                                )
                                            )
                                        )
                                        Toast.makeText(context , "sending with coordinates" , Toast.LENGTH_LONG).show()
                                    }
                                    else {
                                        context.startActivity(
                                            Intent(
                                                Intent
                                                    .ACTION_VIEW,
                                                Uri.parse(
                                                    String.format(
                                                        "https://api.whatsapp.com/send?phone=%s&text=%s",
                                                        allwhatsappContacts[0].number,
                                                        allwhatsappContacts[0].message
                                                    )
                                                )
                                            )
                                        )
                                        Toast.makeText(context , "sending without location coordinates" , Toast.LENGTH_LONG).show()
                                    }
                                }

                        }
                        }catch (_:Error){}

                        viewModel.updateDialogState(
                            viewModel.showWhatsappDialog,
                            Screens.WhatsappScreen.name
                        )
                    }

                ) {
                    Text("confirm")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        viewModel.updateDialogState(
                            viewModel.showWhatsappDialog,
                            Screens.WhatsappScreen.name
                        )
                    }
                ) {
                    Text(text = "Abort")
                }
            },
            title = { Text(text = "Warning!") },
            text = { Text(text = "you are about to perform a critical task.Please confirm to proceed") }
        )
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BelowLogos(
    goToMedScreen : ()->Unit,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Card(
            colors = CardColors(
                containerColor = Color(0xff90e0ef),
                contentColor = Color(0xff03045e),
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .border(2.dp, Color.Black, CircleShape)
                .weight(1f)
                .clickable { }
        ) {
            Box(
            ) {
                Text(
                    text = "Medical Information",
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,

                    modifier = Modifier
                        .clickable {
                            goToMedScreen()
                        }
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Card(
            colors = CardColors(
                containerColor = Color(0xffffd500),
                contentColor = Color(0xffd90429),
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .border(2.dp, Color.Black, CircleShape)
                .weight(1f)
                .clickable { }
        ) {
            Box(
                modifier = modifier
                    .combinedClickable(
                        enabled = true,
                        onClick = {},
                        onLongClick = {

                            val u = Uri.parse("tel:" + "+919315225533")

                            val i = Intent(Intent.ACTION_DIAL, u)

                            try {
                                ctx.startActivity(i)
                            } catch (e: SecurityException) {
                                Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    )
            ) {
                Text(
                    text = "Call Emergency",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp,

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