package com.example.sendmessagefirstdraft.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sendmessagefirstdraft.R
import com.example.sendmessagefirstdraft.db.SmsList
import com.example.sendmessagefirstdraft.db.WhatsappList
import com.example.sendmessagefirstdraft.navigation.Screens

@Composable
fun WhatsappScreen(
    navController: NavController,
    viewModel: WhatsappScreenViewModel,
    modifier: Modifier = Modifier
) {

    val allWhatsappContacts by viewModel.getAllWhatsappContacts().observeAsState()
    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddWhatsappContact.name) }
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
                
            )

            allWhatsappContacts?.let {
                if (allWhatsappContacts!!.isNotEmpty()){
                    LazyColumn {
                        items(allWhatsappContacts!!) {each->
                            EachWhatsappContactCard(
                                deleteFromWhatsappContact = { viewModel.deleteFromWhatsapp(each.id) },
                                whatsappList = each
                            )
                        }
                    }
                }
                else{
                    Text(text = "no contacts added")
                }
            }
        }
    }
}

@Composable
fun EachWhatsappContactCard(
    deleteFromWhatsappContact : ()->Unit,
    whatsappList: WhatsappList,
    modifier: Modifier = Modifier
) {

    Card {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

                ){
                Text(text = whatsappList.number)

                Spacer(modifier = Modifier.width(32.dp))

                Text(text = whatsappList.name)

                Spacer(modifier = Modifier.width(32.dp))

                IconButton(
                    onClick = deleteFromWhatsappContact
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }

            }
            Text(text = whatsappList.message)
        }
    }

}

@Composable
fun AddMoreWhatsappContact(
    navController: NavController,
    whatsappScreenViewModel: WhatsappScreenViewModel,
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
    
    val context = LocalContext.current
    
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
            onClick = {
                if (number.isNotEmpty() && name.isNotEmpty() && message.isNotEmpty()) {
                    whatsappScreenViewModel.insertIntoWhatsapp(WhatsappList(number = number , name = name , message = message))
                    navController.popBackStack()
                }
                else{
                    Toast.makeText(context , "Invalid Field(s)" , Toast.LENGTH_LONG).show()
                }
            }
        ) {
            Text(text = "save whatsapp contact")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewFxn() {
//    WhatsappScreen()

}