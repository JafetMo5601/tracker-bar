package com.finalproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SupportMessage(
    var id: Int,
    var user: String,
    var message: String
): Parcelable {
    constructor():
            this(0, "", "")
}