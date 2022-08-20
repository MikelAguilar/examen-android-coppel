package com.miguelaguilar.superherocodechallengecoppel.domain.dmodels

import androidx.room.ColumnInfo

data class SuperHeroAdditionalItemsEntityModel(
    val superHeroId : Int? = 0,
    val resourceURI : String? = "",
    val name : String? = "",
    val fromParentModule : String? = ""
)