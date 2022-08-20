package com.miguelaguilar.superherocodechallengecoppel.domain.dmodels

import androidx.room.ColumnInfo

data class SuperHeroAdditionalDataEntityModel(
    val superHeroId : Int? = 0,
    val collectionURI : String? = "",
    val available : Int? = 0,
    val fromModule : String? = ""
)