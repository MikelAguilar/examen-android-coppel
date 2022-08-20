package com.miguelaguilar.superherocodechallengecoppel.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelaguilar.superherocodechallengecoppel.adapters.ItemLinksAdapter
import com.miguelaguilar.superherocodechallengecoppel.adapters.SuperHeroAdapter
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import com.miguelaguilar.superherocodechallengecoppel.domain.usercase.GetHerosUserCase
import com.miguelaguilar.superherocodechallengecoppel.domain.usercase.GetSpecificHeroUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val getHerosUserCase: GetHerosUserCase,
    private val getSpecificHeroUserCase: GetSpecificHeroUserCase
) : ViewModel() {

    val superHeroModelResponse = MutableLiveData<SuperHeroModel>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Int>()

    //Adapter
    val superHeroItemAdapter: SuperHeroAdapter = SuperHeroAdapter()
    val itemLinkAdapter : ItemLinksAdapter = ItemLinksAdapter()

    fun toGetAllHerosData() {
        viewModelScope.launch {
            isLoading.postValue(View.VISIBLE)
            val result = getHerosUserCase()
            when (result) {
                is SuperHeroModel -> {
                    superHeroModelResponse.postValue(result)
                    isLoading.postValue(View.GONE)
                }
                is String -> {
                    isLoading.postValue(View.GONE)
                    errorMessage.value = result
                }
            }
        }
    }

    fun toGetSpecificHeroData(id: Int?) {
        isLoading.postValue(View.VISIBLE)
        viewModelScope.launch {
            val result = getSpecificHeroUserCase(id ?: -1)
            when (result) {
                is SuperHeroModel -> {
                    superHeroModelResponse.postValue(result)
                    isLoading.postValue(View.GONE)
                }
                is String -> {
                    isLoading.postValue(View.GONE)
                    errorMessage.value = result
                }
            }
        }
    }
}