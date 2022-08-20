package com.miguelaguilar.superherocodechallengecoppel.data.model.response

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("resourceURI") val resourceURI: String? = null,
    @SerializedName("name") val name: String? = null
)