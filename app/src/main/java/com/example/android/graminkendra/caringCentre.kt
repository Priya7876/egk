package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class caringCentre(
    var caringCentreName :String = "",
    var caringCentreAddress :String = "",
    var centrePhoneNumber :String = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(caringCentreName)
        parcel.writeString(caringCentreAddress)
        parcel.writeString(centrePhoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<caringCentre> {
        override fun createFromParcel(parcel: Parcel): caringCentre {
            return caringCentre(parcel)
        }

        override fun newArray(size: Int): Array<caringCentre?> {
            return arrayOfNulls(size)
        }
    }
}