package com.example.sendmessagefirstdraft.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sendmessagefirstdraft.MainApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MedInfoViewModel : ViewModel() {

    //get med info dao
    val MedInfoDao = MainApplication.contactsDatabase.getMedDao()


    //date picker visibility logic
    private var _isDatePickerVisible = MutableStateFlow(false)
    val isDatePickerVisible = _isDatePickerVisible.asStateFlow()

    //fxn to update date picker visibility
    fun updateDatePickerVisibility() {
        _isDatePickerVisible.value = isDatePickerVisible.value.not()
    }

    private val _whichMedInfoDialogToShow = MutableStateFlow(mutableMapOf("name" to false , "allergy" to false , "address" to false , "medicalNote" to false))
    val whichMedInfoDialogToShow = _whichMedInfoDialogToShow.asStateFlow()


    fun medInfoDialogVisibilityUpdate(key : String) {
        _whichMedInfoDialogToShow.value[key] = !whichMedInfoDialogToShow.value[key]!!
    }


//    private var _medNameInfo = MutableStateFlow(false)
//    val medNameInfo = _medNameInfo.asStateFlow()
//
//    private var _medAllergyInfo = MutableStateFlow(false)
//    val medAllergyInfo = _medAllergyInfo.asStateFlow()
//
//    private var _medAddressInfo = MutableStateFlow(false)
//    val medAddressInfo = _medAddressInfo.asStateFlow()
//
//    private var _medMedicalNoteInfo = MutableStateFlow(false)
//    val medMedicalNoteInfo = _medMedicalNoteInfo.asStateFlow()

    //update name

    //update dob

    //update blood group

    //update allergies

    //update address


    //update medical info

}