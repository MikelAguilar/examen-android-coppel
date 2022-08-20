package com.miguelaguilar.superherocodechallengecoppel.adapters.viewholders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.ItemModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroUrlsModel

class ItemLinkViewModel : ViewModel() {
    val name : MutableLiveData<String> = MutableLiveData()
    val link : MutableLiveData<String> = MutableLiveData()
    fun bind (item : ItemModel) {
        name .value = item.name ?: ""
        link.value = item.resourceURI ?: ""
    }
    fun bindUrls (item : SuperHeroUrlsModel) {
        name .value = item.type ?: ""
        link.value = item.url ?: ""
    }
}

