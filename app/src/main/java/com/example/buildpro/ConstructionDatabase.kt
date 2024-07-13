package com.example.buildpro.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buildpro.inventorymanagement.ConstructionItem
import androidx.room.Query
import com.example.buildpro.ConstructionItemDao

@Database(entities = [ConstructionItem::class], version = 1)
abstract class ConstructionDatabase : RoomDatabase() {
    abstract fun constructionItemDao(): ConstructionItemDao
}