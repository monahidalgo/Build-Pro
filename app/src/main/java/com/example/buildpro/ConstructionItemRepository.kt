package com.example.buildpro

import com.example.buildpro.data.ConstructionDatabase
import com.example.buildpro.inventorymanagement.ConstructionItem

class ConstructionItemRepository(private val database: ConstructionDatabase) {
    fun getAllItems(): List<ConstructionItem> {
        return database.constructionItemDao().getAllItems()
    }
}