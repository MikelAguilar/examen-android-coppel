package com.miguelaguilar.superherocodechallengecoppel.domain.usercase

import com.miguelaguilar.superherocodechallengecoppel.data.SuperHeroRepository
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import javax.inject.Inject

class GetSpecificHeroUserCase @Inject constructor(private val repository: SuperHeroRepository){
    suspend operator fun invoke(id : Int) : Any{
        return when (val superHero = repository.getSpecificHeroDataFromApi(id)){
            is SuperHeroModel -> {
                if (superHero.code == 200){
                    superHero
                }
                else {
                    //superHero
                    repository.getSpecificHeroDataFromDataBase(id)
                }
            }
            else -> superHero
        }
    }
}