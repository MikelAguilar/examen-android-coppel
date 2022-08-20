package com.miguelaguilar.superherocodechallengecoppel.domain.dmodels

import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.*

data class SuperHerosDatabaseModel(
    var heroResponse: SuperHeroResponseEntity,
    var heroEntity: List<SuperHeroEntity>,
    var heroAdditionalItems: List<SuperHeroAdditionalItemsEntityModel>,
    var heroAdditionalData: List<SuperHeroAdditionalDataEntityModel>,
    var heroUrls: List<SuperHeroUrlsEntity>

)