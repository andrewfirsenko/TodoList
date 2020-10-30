package ru.cutepool.todolist.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var text: String?,
    var timestamp: Long,
    var done: Boolean
) : Parcelable {

    constructor() : this(
        id = 0,
        text = null,
        timestamp = System.currentTimeMillis(),
        done = false
    )

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (text != other.text) return false
        if (timestamp != other.timestamp) return false
        if (done != other.done) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + done.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(text)
        parcel.writeLong(timestamp)
        parcel.writeByte(if (done) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}
