package com.example.sendmessagefirstdraft.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedInfo(

    @PrimaryKey(autoGenerate = true)
    val id :Int = 0 ,

    var name : String? = null ,

    var dob : String? = null ,

    var bloodgrp : String? = null ,

    var allergies : String? = null ,

    var address : String? = null ,

    var medicalNotes : String? = null ,

    var organDonor : String? = null,


)
