package com.sapan.social.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_info")
class ContactEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val infoType: Int,

    @ColumnInfo(name = "infoInt1") val int1: Int?,

    @ColumnInfo(name = "infoInt2") val int2: Int?,

    @ColumnInfo(name = "infoInt3") val int3: Int?,

    @ColumnInfo(name = "infoText1") val text1: String?,

    @ColumnInfo(name = "infoText2") val text2: String?,
    
    @ColumnInfo(name = "infoText3") val text3: String?
)
