package com.example.adi.learnshala.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workshopTable")
data class WorkshopEntity (
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var location: String = "",
    var from: String = "",
    var to: String = "",
    var organisation: String = "",
    var registered: Int = 0,
    var description: String = ""
)