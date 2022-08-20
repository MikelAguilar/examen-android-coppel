package com.miguelaguilar.superherocodechallengecoppel.data.model.response

import com.google.gson.annotations.SerializedName

data class SuperHeroModel(
    @SerializedName("code") val code: Int? = 0,
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("attributionText") val attributionText: String? = null,
    @SerializedName("data") val data: SuperHeroDataModel? = null
)