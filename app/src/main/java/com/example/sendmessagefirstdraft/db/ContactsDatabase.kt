package com.example.sendmessagefirstdraft.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [SmsList::class , WhatsappList::class , MedInfo::class],
    version = 2,
    exportSchema = false
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun getContactsDao() :ContactsDao

    abstract fun getMedDao() : MedDao

    companion object {
        const val NAME = "Contacts_DB"
    }
}