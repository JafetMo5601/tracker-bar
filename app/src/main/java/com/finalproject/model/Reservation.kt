package com.finalproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reservation(
    var id: String,
    val userId: String,
    val barName: String,
    val detail: String,
    val date: String,
): Parcelable {
    constructor():
            this("", "", "", "", "")
}