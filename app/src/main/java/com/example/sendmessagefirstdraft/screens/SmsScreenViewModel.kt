package com.example.sendmessagefirstdraft.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sendmessagefirstdraft.MainApplication
import com.example.sendmessagefirstdraft.db.SmsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmsScreenViewModel : ViewModel() {

    val contactsDao = MainApplication.contactsDatabase.getContactsDao()



    fun insertIntoSms(smsList: SmsList) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsDao.insertIntoSms(smsList)
        }
    }

    fun deleteFromSms(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsDao.deleteSmsContact(id)
        }
    }

    fun getSmsContacts(): LiveData<List<SmsList>> {
        return contactsDao.getAllSms()
    }
}