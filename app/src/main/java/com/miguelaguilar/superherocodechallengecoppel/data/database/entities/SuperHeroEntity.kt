package com.miguelaguilar.superherocodechallengecoppel.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_ENTITY

@Entity(tableName = SUPER_HERO_ENTITY)
data class SuperHeroEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name ="id") val id : Int = 0,
    @ColumnInfo(name = "name") val name : String? = "",
    @ColumnInfo(name = "description") val description : String? = "",
    @ColumnInfo(name = "path") val path : String? = "",
    @ColumnInfo(name = "extension") val extension : String? = ""
)