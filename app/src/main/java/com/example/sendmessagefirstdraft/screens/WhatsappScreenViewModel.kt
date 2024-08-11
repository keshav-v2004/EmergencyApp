package com.example.sendmessagefirstdraft.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sendmessagefirstdraft.MainApplication
import com.example.sendmessagefirstdraft.db.WhatsappList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WhatsappScreenViewModel : ViewModel() {

    val contactsDao = MainApplication.contactsDatabase.getContactsDao()


    fun insertIntoWhatsapp(whatsappList: WhatsappList) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsDao.insertIntoWhatsapp(whatsappList)
        }
    }

    fun deleteFromWhatsapp(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsDao.deleteWhatsappContact(id)
        }
    }

    fun getAllWhatsappContacts() : LiveData<List<WhatsappList>> {
        return contactsDao.getAllWhatsapp()
    }
}