package com.miguelaguilar.superherocodechallengecoppel.domain.usercase

import com.miguelaguilar.superherocodechallengecoppel.data.SuperHeroRepository
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import javax.inject.Inject


class GetHerosUserCase @Inject constructor( private val repository : SuperHeroRepository) {
    suspend operator fun invoke() : Any{
        return when (val superHeros = repository.getAllSuperHerosFromApi()){
            is SuperHeroModel -> {
                if (superHeros.code == 200){
                    if(superHeros.data?.offset == 0) {
                        repository.clearAllHerosData()
                        repository.insertAllHerosFromApi(superHeros)
                        superHeros
                    }
                    else {
                        repository.insertAllHerosFromApi(superHeros)
                        superHeros
                    }
                }

                else {
                    repository.getAllSuperHerosFromDataBase()
                }
            }
            else -> superHeros
        }
    }
}