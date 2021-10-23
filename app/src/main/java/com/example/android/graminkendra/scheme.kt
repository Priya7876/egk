package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class scheme (
    var schemeName:String ="",
    var dueDateScheme:String ="",
    var hyperLink:String ?=null,
    var description:String ="",
    var steps :String=""
        ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,

        ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(schemeName)
        parcel.writeString(dueDateScheme)
        parcel.writeString(hyperLink)
        parcel.writeString(description)
        parcel.writeString(steps)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<scheme> {
        override fun createFromParcel(parcel: Parcel): scheme {
            return scheme(parcel)
        }

        override fun newArray(size: Int): Array<scheme?> {
            return arrayOfNulls(size)
        }
    }
}
