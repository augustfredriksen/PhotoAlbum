package com.example.oblig3_0_3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val name : String,
    val email : String,
/*    val address: Address,
    val phone : String,
    val website : String,
    val company: Company*/
)
