package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class meeting (
    var eventName:String ="",
    var dateTime :String="",
    var meetDes :String ="",
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
        parcel.writeString(eventName)
        parcel.writeString(dateTime)
        parcel.writeString(meetDes)
        parcel.writeStringList(assignedTo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<meeting> {
        override fun createFromParcel(parcel: Parcel): meeting {
            return meeting(parcel)
        }

        override fun newArray(size: Int): Array<meeting?> {
            return arrayOfNulls(size)
        }
    }
}
