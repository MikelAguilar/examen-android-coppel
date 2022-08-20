package com.miguelaguilar.superherocodechallengecoppel.adapters.viewholders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroResultsModel

class ItemSuperHeroViewModel : ViewModel() {
    val superHeroName : MutableLiveData<String> = MutableLiveData()
    val copyright : MutableLiveData<String> = MutableLiveData()
    val description : MutableLiveData<String> = MutableLiveData()
    fun bind(crResponse : String, herodata : SuperHeroResultsModel){
        superHeroName.value = herodata.name ?: ""
        copyright.value = crResponse
        description.value = herodata.description ?: ""
    }
}