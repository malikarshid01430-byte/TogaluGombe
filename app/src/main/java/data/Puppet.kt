package com.example.togalugombe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puppets")
data class Puppet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val role: String,
    val description: String,
    val image: String,
    val isSaved: Boolean = false
)