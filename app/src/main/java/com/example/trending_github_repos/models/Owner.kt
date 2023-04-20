package com.example.trending_github_repos.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity
data class Owner(
    val avatar_url: String?,
    val id: Int,
    val node_id: String?,
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(this.avatar_url)
        dest.writeValue(this.node_id)

    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }
}