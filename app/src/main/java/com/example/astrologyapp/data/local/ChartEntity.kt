package com.example.astrologyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_charts")
data class ChartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val dateOfBirth: String,
    val placeOfBirth: String,
    val chartJson: String,
    val createdAt: Long
)
