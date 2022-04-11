package com.finalproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bar (
    var id: String,
    var name: String,
    var direction: String,
    var status: Boolean,
    var peopleQty: Int,
    var tableQty: Int
): Parcelable {
    constructor():
            this("", "", "", true, 0, 0)
}