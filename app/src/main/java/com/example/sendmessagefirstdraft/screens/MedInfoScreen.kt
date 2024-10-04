package com.example.sendmessagefirstdraft.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


val listOfHeadings = listOf("name" , "allergies" , "address" , "medical notes")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedInfo(
    medInfoViewModel: MedInfoViewModel,
    modifier: Modifier = Modifier
) {

    val datePickerState = rememberDatePickerState()
    Log.i("initial date selected" , datePickerState.selectedDateMillis.toString())

    val medDetails by medInfoViewModel.MedInfoDao.getMedInfo().observeAsState()

    val datePickerVisibility by medInfoViewModel.isDatePickerVisible.collectAsState()

    if (datePickerVisibility) {
        datePicker(
            hideDatePicker = {medInfoViewModel.updateDatePickerVisibility()},
            datePickerState,
        )
    }


    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier

            .fillMaxSize()
            .padding(25.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "name")
        Text(
            text = "${medDetails?.name}",
            fontSize = 45.sp,
            modifier = Modifier
                .weight(1f)
                .clickable {

                }
        )

        Text(text = "date of birth")
        Text(
            text = "${medDetails?.dob}",
            fontSize = 45.sp,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    medInfoViewModel.updateDatePickerVisibility()
                }
        )

        
        Text(text = "blood group")
        Text(
            text = "${medDetails?.bloodgrp}",
            fontSize = 45.sp,

            modifier = Modifier
                .weight(1f)
                .clickable {

                }
        )

        Text(text = "allergies")
        Text(
            text = "${medDetails?.allergies}",
            fontSize = 45.sp,

            modifier = Modifier
                .weight(1f)
                .clickable {

                }
        )

        Text(text = "address")
        Text(
            text = "${medDetails?.address}",
            fontSize = 45.sp,

            modifier = Modifier
                .weight(1f)
                .clickable {

                }
        )

        Text(text = "medical notes")
        Text(
            text = "${medDetails?.medicalNotes}",
            fontSize = 45.sp,
            modifier = Modifier
                .weight(1f)
                .clickable {

                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePicker(
    hideDatePicker: () -> Unit,
    datePickerState: DatePickerState,
    modifier: Modifier = Modifier
) {


    DatePickerDialog(

        onDismissRequest = {
            hideDatePicker()
        },
        dismissButton = {
            Button(
                onClick = {
                    hideDatePicker()
                }
            ) {
                Text(text = "abort")
            }
        },
        confirmButton = {
            Button(
                onClick = {

                    datePickerState.selectedDateMillis?.let {
                        convertMillisToDate(it)
                    }?.let {
                        Log.i(
                            "date after clicking",
                            it
                        )
                    }
                    hideDatePicker()

                }
            ) {
                Text(text = "set as date of birth")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = true,
        )
    }

}

@Composable
fun EditableMedicalInfo(
    MedInfoHeading : String,
    alreadySavedInfo : String,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
        },

        confirmButton = {
            Button(
                onClick = {
                }
            ) {
                Text(text = "OK")
            }}
        ,
        dismissButton = {
            OutlinedButton(
                onClick =  {
                }
            ) {
                Text(text = "Cancel")
            }
        },
        title = {
            Text(
                text = MedInfoHeading,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
        ) },
        text = {
            OutlinedTextField(
                value = alreadySavedInfo,
                onValueChange = {}
            )
        }
    )
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview(showSystemUi = false)
@Composable
private fun prevv() {
    val some = listOf("allergies", "name" , "address" , "medical notes")
    EditableMedicalInfo(MedInfoHeading = some[2] , alreadySavedInfo = "nothing as such")
}
