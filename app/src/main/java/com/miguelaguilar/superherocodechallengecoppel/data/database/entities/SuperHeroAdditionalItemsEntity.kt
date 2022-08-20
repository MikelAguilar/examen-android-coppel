package com.miguelaguilar.superherocodechallengecoppel.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_ADDITIONAL_ITEMS
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_ADDITONAL_DATA

@Entity(tableName = SUPER_HERO_ADDITIONAL_ITEMS)
data class SuperHeroAdditionalItemsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "superHeroId") val superHeroId : Int,
    @ColumnInfo(name = "resourceURI") val resourceURI : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "fromParentModule") val fromParentModule : String
)