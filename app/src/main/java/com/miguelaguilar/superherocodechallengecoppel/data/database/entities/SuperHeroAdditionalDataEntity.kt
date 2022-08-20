package com.miguelaguilar.superherocodechallengecoppel.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_ADDITONAL_DATA

@Entity(tableName = SUPER_HERO_ADDITONAL_DATA)
data class SuperHeroAdditionalDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "superHeroId") val superHeroId : Int,
    @ColumnInfo(name = "collectionURI") val collectionURI : String,
    @ColumnInfo(name = "available") val available : Int,
    @ColumnInfo(name = "fromModule") val fromModule : String
)