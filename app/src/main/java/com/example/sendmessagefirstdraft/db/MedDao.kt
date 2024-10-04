package com.example.sendmessagefirstdraft.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface MedDao {


    @Query("select * from MedInfo")
    fun getMedInfo() : LiveData<MedInfo>


    @Query("update MedInfo set name = :name where id = :id")
    fun updateName(name : String , id : Int)

    @Query("update MedInfo set dob = :dob where id = :id")
    fun updateDOB(dob : String , id : Int)

    @Query("update MedInfo set bloodgrp = :bloodGrp where id = :id")
    fun updateBloodGroup(bloodGrp : String , id : Int)

    @Query("update MedInfo set allergies = :allergies where id = :id")
    fun updateAllergies(allergies : String , id : Int)

    @Query("update MedInfo set address = :address where id = :id")
    fun updateAddress(address : String , id : Int)

    @Query("update MedInfo set medicalNotes = :MedNotes where id = :id")
    fun updateMedicalNotes(MedNotes : String , id : Int)

    @Query("update MedInfo set organDonor = :organDonor where id = :id")
    fun updateOrganDonor(organDonor : String , id : Int)


}