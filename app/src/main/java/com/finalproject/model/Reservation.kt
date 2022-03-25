package com.finalproject.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reservation")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "user_id")
    val userId: Int?,

    @ColumnInfo(name = "bar_name")
    val barName: String?,

    @ColumnInfo(name = "detail")
    val detail: String?,

    @ColumnInfo(name = "date")
    val date: String?,
): Parcelable