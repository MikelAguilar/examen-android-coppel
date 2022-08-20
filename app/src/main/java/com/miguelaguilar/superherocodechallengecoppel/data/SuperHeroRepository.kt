package com.miguelaguilar.superherocodechallengecoppel.data

import com.miguelaguilar.superherocodechallengecoppel.data.database.dao.SuperHeroDao
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import com.miguelaguilar.superherocodechallengecoppel.data.network.RequestService
import com.miguelaguilar.superherocodechallengecoppel.domain.mappers.toDatabase
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    private val api : RequestService,
    private val shDao : SuperHeroDao
) {

    suspend fun getAllSuperHerosFromApi(): Any {
        return api.getAllHeros()
    }

    suspend fun getAllSuperHerosFromDataBase(): SuperHeroModel {
        return shDao.getAllHeros()
    }

    suspend fun getSpecificHeroDataFromApi(heroId : Int?) : Any{
        return api.getSpecificHeroData(id = heroId)
    }

    suspend fun getSpecificHeroDataFromDataBase(heroId : Int?): SuperHeroModel {
        return shDao.getAllHeros(heroId)
    }

    suspend fun insertAllHerosFromApi(superHerosModelToDataBase : SuperHeroModel){
        shDao.insertAllHeros(superHerosModelToDataBase.toDatabase())
    }

    suspend fun clearAllHerosData(){
        shDao.clearAllHerosData()
    }
}