package com.miguelaguilar.superherocodechallengecoppel.domain.mappers


import android.util.Log
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroAdditionalDataEntity
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroAdditionalItemsEntity
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.SuperHeroUrlsEntity
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.*
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHeroAdditionalDataEntityModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHeroAdditionalItemsEntityModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHerosResponseModel

fun SuperHerosResponseModel.toResponse(): List<SuperHeroResultsModel> {
    val superHeroResultsList: MutableList<SuperHeroResultsModel> = mutableListOf()
    superHeroEntity.map { entityItem ->

        superHeroResultsList.add(
            SuperHeroResultsModel(
                id = entityItem.id ?: 0,
                name = entityItem.name ?: "",
                description = entityItem.description ?: "",
                thumbnail = SuperHeroThumbnailModel(
                    path = entityItem.path ?: "",
                    extension = entityItem.extension ?: ""
                ),
                comics = getGeneralInfo(
                    superHerosItem.filter { it.superHeroId == entityItem.id && it.fromParentModule == "comics" },
                    superHeroData.find { it.superHeroId == entityItem.id && it.fromModule == "comics" }
                ),
                series = getGeneralInfo(
                    superHerosItem.filter { it.superHeroId == entityItem.id && it.fromParentModule == "series" },
                    superHeroData.find { it.superHeroId == entityItem.id && it.fromModule == "series" }
                ),
                stories = getGeneralInfo(
                    superHerosItem.filter { it.superHeroId == entityItem.id && it.fromParentModule == "stories" },
                    superHeroData.find { it.superHeroId == entityItem.id && it.fromModule == "stories" }
                ),
                events = getGeneralInfo(
                    superHerosItem.filter { it.superHeroId == entityItem.id && it.fromParentModule == "events" },
                    superHeroData.find { it.superHeroId == entityItem.id && it.fromModule == "events" }
                ),
                urls = getHeroUrls(superHerosUrsl.filter { it.superHeroId == entityItem.id })
            )
        )
    }
    return superHeroResultsList
}

fun getGeneralInfo(
    heroItem: List<SuperHeroAdditionalItemsEntityModel>,
    heroData: SuperHeroAdditionalDataEntityModel?,
): GeneralInfoModel {
    val itemListToAdd: MutableList<ItemModel> = mutableListOf()
    heroItem.let {
        it.map { additionalItem ->
            itemListToAdd.add(
                ItemModel(
                    resourceURI = additionalItem.resourceURI,
                    name = additionalItem.name
                )
            )
        }
    }
    return GeneralInfoModel(
        available = heroData?.available ?: 0,
        collectionURI = heroData?.collectionURI ?: "",
        items = itemListToAdd
    )
}

fun getHeroUrls(heroUrls: List<SuperHeroUrlsEntity>?): List<SuperHeroUrlsModel> {
    val heroUrlsList: MutableList<SuperHeroUrlsModel> = mutableListOf()
    heroUrls.let {
        it?.map { urlsItem ->
            heroUrlsList.add(
                SuperHeroUrlsModel(
                    type = urlsItem.type ?: "",
                    url = urlsItem.url ?: ""
                )
            )
        }
    }
    return heroUrlsList
}

fun List<SuperHeroAdditionalDataEntity>.toResponse() : List<SuperHeroAdditionalDataEntityModel>{
    val listToResponse : MutableList<SuperHeroAdditionalDataEntityModel> = mutableListOf()
    let { itList ->
        itList.forEach {
            listToResponse.add(
                SuperHeroAdditionalDataEntityModel(
                    superHeroId = it.superHeroId ?: 0,
                    collectionURI = it.collectionURI ?: "",
                    available = it.available ?: 0,
                    fromModule = it.fromModule ?: ""
                )
            )
        }

    }
    return listToResponse
}

@JvmName("toResponseSuperHeroAdditionalItemsEntity")
fun List<SuperHeroAdditionalItemsEntity>.toResponse() : List<SuperHeroAdditionalItemsEntityModel>{
    val listToResponse : MutableList<SuperHeroAdditionalItemsEntityModel> = mutableListOf()
    let { itList ->
        itList.forEach {
            listToResponse.add(
                SuperHeroAdditionalItemsEntityModel(
                    superHeroId = it.superHeroId ?: 0,
                    resourceURI = it.resourceURI ?: "",
                    name = it.name ?:"",
                    fromParentModule = it.fromParentModule ?:""
                )
            )
        }

    }
    return listToResponse
}