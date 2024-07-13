package com.example.buildpro.inventorymanagement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "construction_items")
data class ConstructionItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val quantity: Int,
    val imageResourceId: String
)

