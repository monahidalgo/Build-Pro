package com.example.buildpro.inventorymanagement
// Moved to inventory package

data class ConstructionItem(
    val id: Int,
    val name: String,
    val description: String,
    val quantity: Int,
    val itemType: String, // Renamed for clarity
    val imageResourceId: Int, // Changed to Int for resource IDs
    val project: String
)
