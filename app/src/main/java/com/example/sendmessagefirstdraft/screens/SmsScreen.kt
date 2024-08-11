package com.example.sendmessagefirstdraft.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
fun SmsScreen(
    viewModel: SmsScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val allSmsContacts by viewModel.getSmsContacts().observeAsState()
    
    
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

            allSmsContacts?.let {
                if (allSmsContacts!!.isNotEmpty()){
                    LazyColumn(
                        modifier = modifier
                            .weight(1f)
                    ) {
                        items(allSmsContacts!!) { each->
                            EachSmsContactCard(smsList = each , deleteSmsContact = {viewModel.deleteFromSms(each.id)})
                        }
                    }
                }else{
                    Text(text = "No contacts added")
                }
            }
            
        }
    }

}

@Composable
fun EachSmsContactCard(
    deleteSmsContact : ()->Unit,
    smsList: SmsList,
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
                Text(text = smsList.number)

                Spacer(modifier = Modifier.width(32.dp))

                Text(text = smsList.name)

                Spacer(modifier = Modifier.width(32.dp))

                IconButton(
                    onClick = deleteSmsContact
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }

            }
            Text(text = smsList.message)
        }
    }
    
}



@Composable
fun AddMoreSmsContacts(
    smsScreenViewModel: SmsScreenViewModel,
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

    val context = LocalContext.current

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
            placeholder = { Text(text = "enter receiver NUMBER")}
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            placeholder = { Text(text = "enter receiver NAME")}
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
                    smsScreenViewModel.insertIntoSms(SmsList(number = number , name = name , message = message))
                    navController.popBackStack()
                }
                else{
                    Toast.makeText(context , "Invalid Field(s)" , Toast.LENGTH_LONG).show()
                }
            }
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
