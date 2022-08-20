package com.miguelaguilar.superherocodechallengecoppel.injection

import android.content.Context
import androidx.room.Room
import com.miguelaguilar.superherocodechallengecoppel.data.database.SuperHeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "super_hero_database"

    //PARA LA INJECCIÓN DEL CONSTRUCTOR DEL ROOM
    @Singleton
    @Provides
    fun roomProvider(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        SuperHeroDatabase::class.java,
        DATABASE_NAME
    ).build()

    //PARA LA INJECCIÓN DEL DAO DE LA DATABASE
    @Singleton
    @Provides
    fun daoProvider(db: SuperHeroDatabase) = db.getSuperHeroDao()

}

/**
 * Aquí se tiene que hacer la configuración del Room.
 * en este, se puede hacer la migración.
 * Se configura naturalmente con una Migration y, en la configuración del provider, se asigna
 * la migración con .migration(VERSION_1_2)
 *
 * En los paréntesis, se especifica que cambia de versión, la cuál va a ser VERSION_1 (que es la versión
 * de donde se origina y (...._2) la versión a la que va
 */