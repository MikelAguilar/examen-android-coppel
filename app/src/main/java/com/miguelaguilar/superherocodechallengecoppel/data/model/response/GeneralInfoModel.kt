package com.miguelaguilar.superherocodechallengecoppel.data.model.response

import com.google.gson.annotations.SerializedName

data class GeneralInfoModel(
    @SerializedName("available") val available : Int? = -1,
    @SerializedName("collectionURI") val collectionURI : String? = null,
    @SerializedName("items") val items : List<ItemModel>? = null
)