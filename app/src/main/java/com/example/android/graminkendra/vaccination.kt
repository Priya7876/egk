package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class vaccination(
    var vacCentreName :String="",
    var vacAddress:String ="",
    var vacContactNumber :String =""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(vacCentreName)
        parcel.writeString(vacAddress)
        parcel.writeString(vacContactNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<vaccination> {
        override fun createFromParcel(parcel: Parcel): vaccination {
            return vaccination(parcel)
        }

        override fun newArray(size: Int): Array<vaccination?> {
            return arrayOfNulls(size)
        }
    }
}