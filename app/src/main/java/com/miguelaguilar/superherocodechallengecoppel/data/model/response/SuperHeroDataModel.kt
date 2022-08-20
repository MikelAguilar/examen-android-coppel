package com.miguelaguilar.superherocodechallengecoppel.data.model.response

import com.google.gson.annotations.SerializedName

data class SuperHeroDataModel(
    @SerializedName("limit") val limit : Int? = 0,
    @SerializedName("total") val total : Int? =-1,
    @SerializedName("offset") val offset : Int? = 0,
    @SerializedName("results") val results : List<SuperHeroResultsModel>? = null
)