package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class phoneFormat (
    val fieldName :String="",
    val position : String ="",
    val contactOfField : String =""
        ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fieldName)
        parcel.writeString(position)
        parcel.writeString(contactOfField)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<phoneFormat> {
        override fun createFromParcel(parcel: Parcel): phoneFormat {
            return phoneFormat(parcel)
        }

        override fun newArray(size: Int): Array<phoneFormat?> {
            return arrayOfNulls(size)
        }
    }
}