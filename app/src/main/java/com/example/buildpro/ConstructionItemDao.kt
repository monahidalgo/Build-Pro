package com.example.buildpro

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.buildpro.inventorymanagement.ConstructionItem



@Dao
interface ConstructionItemDao {
    @Query("SELECT * FROM construction_items") // Use snake_case for table name
    fun getAllItems(): List<ConstructionItem>

    @Insert
    fun insertItem(item: ConstructionItem)

    @Update
    fun updateItem(item:ConstructionItem)

    @Delete
    fun deleteItem(item: ConstructionItem)
}