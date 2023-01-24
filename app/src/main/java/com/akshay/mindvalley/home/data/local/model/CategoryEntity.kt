package com.akshay.mindvalley.home.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import javax.annotation.concurrent.Immutable

@Entity(
    tableName = "categories",
    indices = [
        Index("name", unique = true)
    ]
)

@Immutable
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String
)