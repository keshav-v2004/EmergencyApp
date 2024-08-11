package com.example.sendmessagefirstdraft.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappList(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val name : String,

    val number : String,

    val message : String
)
