package com.miguelaguilar.superherocodechallengecoppel.domain.dmodels

import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroAdditionalDataEntity
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroAdditionalItemsEntity
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroEntity
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroUrlsEntity

data class SuperHerosResponseModel(
    val superHeroEntity : List<SuperHeroEntity>,
    val superHeroData : List<SuperHeroAdditionalDataEntityModel>,
    val superHerosItem : List<SuperHeroAdditionalItemsEntityModel>,
    val superHerosUrsl : List<SuperHeroUrlsEntity>
)