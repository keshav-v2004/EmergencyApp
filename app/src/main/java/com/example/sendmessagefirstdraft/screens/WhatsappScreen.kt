package com.example.sendmessagefirstdraft.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sendmessagefirstdraft.R
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

            if (allWhatsappContacts.isNullOrEmpty()){
                FloatingActionButton(
                    onClick = { navController.navigate(Screens.AddWhatsappContact.name) }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
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
                contentDescription = null,
                modifier = modifier.clip(RoundedCornerShape(50.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "all saved whatsapp enabled phone numbers will be stored here",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                lineHeight = 25.sp,
                fontWeight = Bold
                
            )

            Spacer(modifier = Modifier.height(16.dp))

            allWhatsappContacts?.let {
                if (allWhatsappContacts!!.isNotEmpty()){
                    LazyColumn(
                        modifier = modifier
                            .padding(2.dp)
                    ) {
                        items(allWhatsappContacts!!) {each->
                            EachWhatsappContactCard(
                                deleteFromWhatsappContact = { viewModel.deleteFromWhatsapp(each.id) },
                                whatsappList = each
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                        }
                    }
                }
                else{
                    Text(
                        text = "no contacts added",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = Medium
                    )
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

    Card(
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified
        ),
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .border(2.dp, Color.White, CircleShape)


    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(10.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                ){
                Text(
                    text = whatsappList.number,
                    fontWeight = Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(32.dp))

                Text(
                    text = whatsappList.name,
                    fontWeight = Bold,
                    fontSize = 25.sp,
                    modifier = Modifier

                )

                Spacer(modifier = Modifier.width(16.dp))

                IconButton(
                    onClick = deleteFromWhatsappContact
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = modifier
                            .size(40.dp)
                            .border(2.dp, Color.White, CircleShape)
                    )
                }

            }
            Text(
                text = whatsappList.message,
                fontWeight = Bold,
                fontSize = 25.sp,
                modifier = Modifier

            )
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
            contentDescription = null,
            modifier = modifier
                .clip(RoundedCornerShape(50.dp))

        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Call,
                    contentDescription = null ,
                    tint = Color.White
                ) },
            value = number,
            onValueChange = {number = it},
            singleLine = true,
            placeholder = {
                Text(
                    text = "enter whatsapp enabled number" ,
                    color = Color.White,
                    fontWeight = Medium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )},
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                disabledTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_person_add_24),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            value = name,
            onValueChange = {name = it},
            placeholder = {
                Text(
                    text = "enter name",
                    color = Color.White,
                    fontWeight = Medium,
                    fontSize = 20.sp,

                )},
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                disabledTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            value = message,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_message_24),
                    contentDescription = null ,
                    tint = Color.White
                )
            },
            onValueChange = {message = it},
            placeholder = {
                Text(
                    text = "enter message",
                    color = Color.White,
                    fontWeight = Medium,
                    fontSize = 20.sp,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                disabledTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
        )

        Spacer(modifier = Modifier.height(45.dp))

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
            Text(
                text = "save whatsapp contact",
                fontSize = 25.sp
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewFxn() {
//    WhatsappScreen()

}