package com.example.fooddatabaseeditor.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val phoneNumber: String,
    val firstName: String,
    val address: String,
    val password: String,
): Parcelable
