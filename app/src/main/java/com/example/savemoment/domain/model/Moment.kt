package com.example.savemoment.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Entity(tableName = "moments")
@Parcelize
data class Moment(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val picture: String? = null,
    val title: String? = null,
    val description: String? = null
) : Parcelable