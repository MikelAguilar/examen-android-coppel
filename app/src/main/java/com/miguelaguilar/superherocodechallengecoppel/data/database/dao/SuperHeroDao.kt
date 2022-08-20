package com.miguelaguilar.superherocodechallengecoppel.data.database.dao

import android.util.Log
import androidx.room.*
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.*
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroDataModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHeroAdditionalDataEntityModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHeroAdditionalItemsEntityModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHerosDatabaseModel
import com.miguelaguilar.superherocodechallengecoppel.domain.dmodels.SuperHerosResponseModel
import com.miguelaguilar.superherocodechallengecoppel.domain.mappers.toResponse
import com.miguelaguilar.superherocodechallengecoppel.utlis.*

@Dao
interface SuperHeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = SuperHeroEntity::class)
    suspend fun insertHeroData(superHeroEntity: List<SuperHeroEntity>)

    @Insert( entity = SuperHeroAdditionalItemsEntity::class)
    suspend fun insertHeroAdditionalItems(superHeroAdditionalItemEntities: List<SuperHeroAdditionalItemsEntityModel>)

    @Insert(entity = SuperHeroAdditionalDataEntity::class)
    suspend fun insertHeroAdditionalData(superHeroAdditionalDatumEntities: List<SuperHeroAdditionalDataEntityModel>)

    @Insert(entity = SuperHeroUrlsEntity::class)
    suspend fun insertHeroUrls(superHeroUrlEntities: List<SuperHeroUrlsEntity>)

    @Insert(entity = SuperHeroResponseEntity::class)
    suspend fun inserHeroResponse(superHeroResponseEntity: SuperHeroResponseEntity)

    @Query("SELECT * FROM $SUPER_HERO_RESPONSE")
    suspend fun getHeroResponse(): SuperHeroResponseEntity

    @Query("SELECT * FROM $SUPER_HERO_ENTITY ORDER BY name ASC")
    suspend fun getAllSuperHeros(): List<SuperHeroEntity>

    @Query("SELECT * FROM $SUPER_HERO_ADDITONAL_DATA")
    suspend fun getSuperheroAdditionalData(): List<SuperHeroAdditionalDataEntity>

    @Query("SELECT * FROM $SUPER_HERO_ADDITIONAL_ITEMS")
    suspend fun getSuperHeroAdditionalItems(): List<SuperHeroAdditionalItemsEntity>

    @Query("SELECT * FROM $SUPER_HERO_URIS")
    suspend fun getSuperHeroUrls() : List<SuperHeroUrlsEntity>


    @Query("SELECT * FROM $SUPER_HERO_ENTITY WHERE id = :id ORDER BY name ASC")
    suspend fun getAllSuperHeros(id : Int): List<SuperHeroEntity>

    //@Query("SELECT * FROM $SUPER_HERO_ADDITONAL_DATA WHERE superHeroId = :id")
    //suspend fun getSuperheroAdditionalData(id : Int): List<SuperHeroAdditionalDataEntity>

    @Query("SELECT * FROM $SUPER_HERO_ADDITIONAL_ITEMS WHERE superHeroId = :id")
    suspend fun getSuperHeroAdditionalItems(id : Int): List<SuperHeroAdditionalItemsEntity>

    @Query("SELECT * FROM $SUPER_HERO_URIS WHERE superHeroId = :id")
    suspend fun getSuperHeroUrls(id : Int) : List<SuperHeroUrlsEntity>


    @Query("DELETE FROM $SUPER_HERO_ADDITIONAL_ITEMS")
    suspend fun deleteAllAdditionalItems()

    @Query("DELETE FROM $SUPER_HERO_ADDITONAL_DATA")
    suspend fun deleteAllAdditionalData()

    @Query("DELETE FROM $SUPER_HERO_ENTITY")
    suspend fun deleteAllHeroes()

    @Query("DELETE FROM $SUPER_HERO_URIS")
    suspend fun deleteAllHerosUrls()

    suspend fun getAllHeros(id : Int? = -1): SuperHeroModel {
        val heroResponse: SuperHeroResponseEntity? = getHeroResponse()
        var heroEntity: List<SuperHeroEntity>? =  null
        var herosData: List<SuperHeroAdditionalDataEntity>? = null
        var herosItem: List<SuperHeroAdditionalItemsEntity>? = null
        var herosUrls : List<SuperHeroUrlsEntity>? = null
        when(id) {
            -1 -> {
                heroEntity = getAllSuperHeros()
                herosData = getSuperheroAdditionalData()
                herosItem = getSuperHeroAdditionalItems()
                herosUrls = getSuperHeroUrls()
            }
            else -> {
                heroEntity = getAllSuperHeros(id!!)
                herosData = getSuperheroAdditionalData()
                herosItem = getSuperHeroAdditionalItems(id)
                herosUrls = getSuperHeroUrls(id)
            }
        }

        return SuperHeroModel(
            copyright = heroResponse?.copyright ?: "",
            attributionText = heroResponse?.attributionText,
            data = SuperHeroDataModel(
                limit = heroResponse?.limit,
                total = heroResponse?.total,
                results = SuperHerosResponseModel(superHeroEntity = heroEntity ?: emptyList(), superHeroData = (herosData).toResponse(), superHerosItem = (herosItem).toResponse(), superHerosUrsl = herosUrls ?: emptyList() ).toResponse()
            )
        )
    }

    suspend fun insertAllHeros(superHerosDatabaseModel: SuperHerosDatabaseModel){
        insertHeroData(superHerosDatabaseModel.heroEntity)
        insertHeroUrls(superHerosDatabaseModel.heroUrls)
        insertHeroAdditionalData(superHerosDatabaseModel.heroAdditionalData)
        insertHeroAdditionalItems(superHerosDatabaseModel.heroAdditionalItems)
        inserHeroResponse(superHerosDatabaseModel.heroResponse)
    }

    suspend fun clearAllHerosData(){
        deleteAllHeroes()
        deleteAllAdditionalItems()
        deleteAllAdditionalData()
        deleteAllHerosUrls()
    }
}