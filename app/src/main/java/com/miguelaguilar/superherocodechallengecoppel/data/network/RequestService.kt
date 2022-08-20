package com.miguelaguilar.superherocodechallengecoppel.data.network

import android.util.Log
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestService @Inject constructor(private val api: AppAPI) {
    suspend fun getAllHeros(): Any {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllHeros()
                response.body() ?: SuperHeroModel()
            } catch (t: Throwable) {
                SuperHeroModel()
            }
        }
    }

    suspend fun getSpecificHeroData(id : Int?): Any {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getSpecificHeroData(id = id ?: 0)
                response.body() ?: SuperHeroModel()
            } catch (t: Throwable) {
                SuperHeroModel()
            }
        }
    }
}
