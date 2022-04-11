package com.finalproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Promotion (
    var id: Int,
    var barName: String,
    var message: String
): Parcelable {
    constructor():
            this(0, "", "")
}