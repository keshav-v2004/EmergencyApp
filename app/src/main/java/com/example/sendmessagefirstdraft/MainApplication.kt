package com.example.sendmessagefirstdraft

import android.app.Application
import androidx.room.Room
import com.example.sendmessagefirstdraft.db.ContactsDatabase

class MainApplication : Application() {

    companion object {

        lateinit var contactsDatabase: ContactsDatabase
    }

    override fun onCreate() {
        super.onCreate()

        contactsDatabase = Room.databaseBuilder(
            applicationContext,
            ContactsDatabase::class.java,
            ContactsDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}