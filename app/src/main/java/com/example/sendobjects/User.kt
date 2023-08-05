package com.example.sendobjects

import android.os.Parcel
import android.os.Parcelable

data class User (val id: Int, val name: String) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel.readInt(), parcel.readString() ?: "")
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
