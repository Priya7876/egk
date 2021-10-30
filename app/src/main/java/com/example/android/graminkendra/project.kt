package com.example.android.graminkendra

import android.os.Parcel
import android.os.Parcelable

data class project (
    var projectName :String="",
    var projectDescription:String="",
    var imagePriject:String ="",
    var startingDate :String ="",
    var progessDetail:Int=0,
    var noOfWorkers:String ="",
    var status :String="",
    var documentId:String=""


                    ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectName)
        parcel.writeString(projectDescription)
        parcel.writeString(imagePriject)
        parcel.writeString(startingDate)
        parcel.writeInt(progessDetail)
        parcel.writeString(noOfWorkers)
        parcel.writeString(status)
        parcel.writeString(documentId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<project> {
        override fun createFromParcel(parcel: Parcel): project {
            return project(parcel)
        }

        override fun newArray(size: Int): Array<project?> {
            return arrayOfNulls(size)
        }
    }

}


