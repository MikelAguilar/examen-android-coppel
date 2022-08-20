package com.miguelaguilar.superherocodechallengecoppel.data.model.response

import com.google.gson.annotations.SerializedName

data class SuperHeroUrlsModel(
    @SerializedName("type") val type : String? = null,
    @SerializedName("url") val url : String? = null
)