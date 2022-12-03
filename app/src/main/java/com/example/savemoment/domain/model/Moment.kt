package com.example.savemoment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moments")
data class Moment(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val pictureUri: String,
    val title: String,
    val description: String?
)