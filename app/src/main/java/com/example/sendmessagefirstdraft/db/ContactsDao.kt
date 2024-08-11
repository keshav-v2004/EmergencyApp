package com.example.sendmessagefirstdraft.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Query("SELECT * FROM SmsList")
    fun getAllSms() : LiveData<List<SmsList>>

    @Query("SELECT * FROM WhatsappList")
    fun getAllWhatsapp() : LiveData<List<WhatsappList>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIntoSms(smsList: SmsList)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIntoWhatsapp(whatsappList: WhatsappList)

    @Query("delete from WhatsappList where id = :id")
    fun deleteWhatsappContact(id : Int)

    @Query("delete from smslist where id = :id")
    fun deleteSmsContact(id : Int)


}