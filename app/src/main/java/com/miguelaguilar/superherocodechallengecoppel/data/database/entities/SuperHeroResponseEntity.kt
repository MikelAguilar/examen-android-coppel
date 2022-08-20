package com.miguelaguilar.superherocodechallengecoppel.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_RESPONSE

@Entity(tableName = SUPER_HERO_RESPONSE)
data class SuperHeroResponseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name ="copyright") val copyright : String? = "",
    @ColumnInfo(name ="attributionText") val attributionText : String? = "",
    @ColumnInfo(name = "limit") val limit : Int? = 0,
    @ColumnInfo(name = "total") val total : Int? =0
)