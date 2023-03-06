package com.buzzed.jpfinder.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jp_list")
data class JP(
     @PrimaryKey(autoGenerate = true)
     val id: Int? = null,
     @ColumnInfo(name = "lastname")
     val lastName: String?,
     @ColumnInfo(name = "firstname")
     val firstName: String?,
     @ColumnInfo(name = "middlename")
     val middleName: String?,
     @ColumnInfo(name = "address1")
     val address1: String?,
     @ColumnInfo(name = "address2")
     val address2: String?,
     @ColumnInfo(name = "community")
     val community: String?,
     @ColumnInfo(name = "email")
     val emailAddress: String?,
)

