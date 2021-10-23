package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class asha (
    val nameOfAsha:String="",
    val wardDetail:String="",
    val phoneNumber:String="",
    val mailAsha:String=""
        ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }



    companion object CREATOR : Parcelable.Creator<asha> {
        override fun createFromParcel(parcel: Parcel): asha {
            return asha(parcel)
        }

        override fun newArray(size: Int): Array<asha?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameOfAsha)
        parcel.writeString(wardDetail)
        parcel.writeString(phoneNumber)
        parcel.writeString(mailAsha)
    }
}