package com.example.android.graminkendra

import android.accounts.AuthenticatorDescription
import android.os.Parcel
import android.os.Parcelable

data class complaint
    (
    var title:String ="",
    var image:String ="",
    var description: String ="",
    var assignedTo:ArrayList<String> = ArrayList()
            ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeStringList(assignedTo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<complaint> {
        override fun createFromParcel(parcel: Parcel): complaint {
            return complaint(parcel)
        }

        override fun newArray(size: Int): Array<complaint?> {
            return arrayOfNulls(size)
        }
    }
}