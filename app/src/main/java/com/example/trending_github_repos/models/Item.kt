package com.example.trending_github_repos.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")

public class Item(

    @PrimaryKey
    val id: Int,
    val description: String?,
    val forks_count: Int,
    val full_name: String?,
    val language: String?,
    val languages_url: String?,
    @Embedded(prefix = "owner")
    val owner: Owner,
    val stargazers_count: Int,
    val isSelected: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt()

    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(this.description)
        dest.writeValue(this.forks_count)
        dest.writeString(this.full_name)
        dest.writeString(this.language)
        dest.writeParcelable(owner, flags)
        dest.writeString(this.languages_url)
        dest.writeValue(this.stargazers_count)
        dest.writeValue(this.isSelected)
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}





