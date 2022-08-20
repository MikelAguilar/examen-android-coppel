package com.miguelaguilar.superherocodechallengecoppel.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_ENTITY
import com.miguelaguilar.superherocodechallengecoppel.utlis.SUPER_HERO_URIS

@Entity(tableName = SUPER_HERO_URIS)
data class SuperHeroUrlsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "superHeroId") val superHeroId : Int = 0,
    @ColumnInfo(name = "url") val url : String,
    @ColumnInfo(name = "type") val type : String,
)