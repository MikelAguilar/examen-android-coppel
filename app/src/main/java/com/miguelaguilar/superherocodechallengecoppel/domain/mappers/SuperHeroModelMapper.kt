package com.miguelaguilar.superherocodechallengecoppel.domain.mappers

import android.util.Log
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.*
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroResultsModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHeroAdditionalDataEntityModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHeroAdditionalItemsEntityModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHerosDatabaseModel
import timber.log.Timber

fun SuperHeroModel.toDatabase() = SuperHerosDatabaseModel(
    heroResponse = SuperHeroResponseEntity(
        copyright = copyright ?: "",
        attributionText = attributionText ?: "",
        limit = data?.limit ?: 0,
        total = data?.total ?: 0
    ),
    heroEntity = getAllResults(data?.results ?: emptyList()),
    heroAdditionalItems = getAllItemList(data?.results ?: emptyList()),
    heroAdditionalData = getAllDatalist(data?.results ?: emptyList()),
    heroUrls = getAllUrlsList(data?.results ?: emptyList())
)

fun getAllResults(superHeroEntity: List<SuperHeroResultsModel>) : List<SuperHeroEntity> {
    val superHeroToAddlist : MutableList<SuperHeroEntity> = mutableListOf()
    superHeroEntity.let {
        it.map { map ->
            superHeroToAddlist.add(SuperHeroEntity(
                id = map.id ?: 0,
                name =map.name ?: "",
                description = map.description ?: "",
                path = map.thumbnail?.path ?: "",
                extension = map.thumbnail?.extension ?: ""
                ))
        }
    }
    return superHeroToAddlist
}

fun getAllItemList(superHeroModel: List<SuperHeroResultsModel>?): List<SuperHeroAdditionalItemsEntityModel> {
    val itemListToAdd: MutableList<SuperHeroAdditionalItemsEntityModel> = mutableListOf()
    superHeroModel?.map { superHeroModelItem->
        superHeroModelItem.comics?.items.let {
            it!!.map { map ->

                itemListToAdd.add(
                    SuperHeroAdditionalItemsEntityModel(
                        superHeroId = superHeroModelItem.id ?: 0,
                        resourceURI = map.resourceURI ?: "",
                        name = map.name ?: "",
                        fromParentModule = "comics"
                    )
                )
            }
        }
        superHeroModelItem.events?.items.let {
            it!!.map { map ->
                itemListToAdd.add(
                    SuperHeroAdditionalItemsEntityModel(
                        superHeroId = superHeroModelItem.id ?: 0,
                        resourceURI = map.resourceURI ?: "",
                        name = map.name ?: "",
                        fromParentModule = "events"
                    )
                )
            }
        }
        superHeroModelItem.series?.items.let {
            it!!.map { map ->
                itemListToAdd.add(
                    SuperHeroAdditionalItemsEntityModel(
                        superHeroId = superHeroModelItem.id ?: 0,
                        resourceURI = map.resourceURI ?: "",
                        name = map.name ?: "",
                        fromParentModule = "series"
                    )
                )
            }
        }
        superHeroModelItem.stories?.items.let {
            it!!.map { map ->
                itemListToAdd.add(
                    SuperHeroAdditionalItemsEntityModel(
                        superHeroId = superHeroModelItem.id ?: 0,
                        resourceURI = map.resourceURI ?: "",
                        name = map.name ?: "",
                        fromParentModule = "stories"
                    )
                )
            }
        }
    }
    return itemListToAdd
}

fun getAllDatalist(superHeroModel: List<SuperHeroResultsModel>?): List<SuperHeroAdditionalDataEntityModel> {
    val dataListToAdd: MutableList<SuperHeroAdditionalDataEntityModel> = mutableListOf()
    superHeroModel?.map { superHeroModelItem ->
        dataListToAdd.add(
            SuperHeroAdditionalDataEntityModel(
                superHeroId = superHeroModelItem.id ?: 0,
                collectionURI = superHeroModelItem.comics?.collectionURI ?: "",
                available = superHeroModelItem.comics?.available ?: 0,
                fromModule = "comics"
            )
        )
        dataListToAdd.add(
            SuperHeroAdditionalDataEntityModel(
                superHeroId = superHeroModelItem.id ?: 0,
                available = superHeroModelItem.series?.available ?: 0,
                collectionURI = superHeroModelItem.series?.collectionURI ?: "",
                fromModule = "series"
            )
        )
        dataListToAdd.add(
            SuperHeroAdditionalDataEntityModel(
                superHeroId = superHeroModelItem.id ?: 0,
                available = superHeroModelItem.stories?.available ?: 0,
                collectionURI = superHeroModelItem.stories?.collectionURI ?: "",
                fromModule = "stories"
            )
        )
        dataListToAdd.add(
            SuperHeroAdditionalDataEntityModel(
                superHeroId = superHeroModelItem.id ?: 0,
                available = superHeroModelItem.events?.available ?: 0,
                collectionURI = superHeroModelItem.events?.collectionURI ?: "",
                fromModule = "events"
            )
        )
    }
    return dataListToAdd
}

fun getAllUrlsList(superHeroModel: List<SuperHeroResultsModel>?): List<SuperHeroUrlsEntity> {
    val urlListToAdd: MutableList<SuperHeroUrlsEntity> = mutableListOf()
    superHeroModel?.map { superHeroModelItem ->
        superHeroModelItem.urls.let {
            it!!.map { map ->
                urlListToAdd.add(
                    SuperHeroUrlsEntity(
                        superHeroId = superHeroModelItem.id ?: 0,
                        type = map.type ?: "",
                        url = map.url ?: ""
                    )
                )
            }
        }
    }
    Timber.e(urlListToAdd.map { it }.toString())
    return urlListToAdd
}