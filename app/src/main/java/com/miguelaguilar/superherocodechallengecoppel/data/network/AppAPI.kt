package com.miguelaguilar.superherocodechallengecoppel.data.network



import com.miguelaguilar.superherocodechallengecoppel.MainApplication
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

interface AppAPI {

    companion object{
        const val HEROES = "characters"
        const val HEROES_DATA = "characters/"
        const val PRIVATE_KEY = "18f75c5969e5e5ace434b60614d5fda35d62231e"
        const val API_KEY = "b690dd727c45072137df09793f454127"
        const val TS = "ts"
        const val API_KEY_NAME = "apikey"
        const val HASH_NAME ="hash"
    }

    @GET(HEROES)
    suspend fun getAllHeros(
        @Query(TS) ts : String? = (System.currentTimeMillis()/1000).toString(),
        @Query(API_KEY_NAME) apikey : String? = API_KEY,
        @Query(HASH_NAME) hash : String? = getMD5Hash((System.currentTimeMillis()/1000).toString(), PRIVATE_KEY, API_KEY),
        @Query("limit") limit : Int? = 50,
        @Query("offset") offset : Int = MainApplication().getOffsetQueryNumber()
    ) : Response<SuperHeroModel>

    @GET("$HEROES_DATA{id}")
    suspend fun getSpecificHeroData(
        @Path("id") id : Int,
        @Query(TS) ts : String? = (System.currentTimeMillis()/1000).toString(),
        @Query(API_KEY_NAME) apikey : String? = API_KEY,
        @Query(HASH_NAME) hash : String? = getMD5Hash((System.currentTimeMillis()/1000).toString(), PRIVATE_KEY, API_KEY),
    ) : Response<SuperHeroModel>
}

fun getMD5Hash(ts : String, privateKey : String, publicKey : String) : String{
    val stringToHashed = "$ts$privateKey$publicKey"
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1,md.digest(stringToHashed.toByteArray())).toString(16).padStart(32,'0')
}