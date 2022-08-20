package com.miguelaguilar.superherocodechallengecoppel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miguelaguilar.superherocodechallengecoppel.data.database.dao.SuperHeroDao
import com.miguelaguilar.superherocodechallengecoppel.data.database.entities.*

@Database(entities = [SuperHeroResponseEntity::class, SuperHeroEntity::class, SuperHeroAdditionalDataEntity::class, SuperHeroAdditionalItemsEntity::class, SuperHeroUrlsEntity::class], version = 1)
abstract class SuperHeroDatabase : RoomDatabase() {
    abstract fun getSuperHeroDao() : SuperHeroDao
}