package com.example.buildpro

class ConstructionItemRepository(private val database: ConstructionDatabase) {
    fun getAllItems(): List<ConstructionItem> {
        return database.constructionItemDao().getAllItems()
    }
}