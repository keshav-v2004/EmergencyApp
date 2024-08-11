package com.example.sendmessagefirstdraft.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [SmsList::class , WhatsappList::class],
    version = 1,
    exportSchema = false
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun getContactsDao() :ContactsDao

    companion object {
        const val NAME = "Contacts_DB"
    }
}