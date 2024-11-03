package com.sumanthacademy.myapplication.model

import android.os.Parcel
import android.os.Parcelable

data class Todo(var image:Int? = null, var title:String, var status:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        image?.let { parcel.writeInt(it) }
        parcel.writeString(title)
        parcel.writeString(status)
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }
}
