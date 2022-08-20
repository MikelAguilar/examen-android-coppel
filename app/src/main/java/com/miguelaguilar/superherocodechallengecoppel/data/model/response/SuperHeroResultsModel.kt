package com.miguelaguilar.superherocodechallengecoppel.data.model.response

import com.google.gson.annotations.SerializedName

data class SuperHeroResultsModel(
    @SerializedName("id") val id : Int? = -1,
    @SerializedName("name") val name : String? = null,
    @SerializedName("description") val description : String? = null,
    @SerializedName("thumbnail") val thumbnail : SuperHeroThumbnailModel? = null,
    @SerializedName("comics") val comics : GeneralInfoModel? = null,
    @SerializedName("series") val series : GeneralInfoModel? = null,
    @SerializedName("stories") val stories : GeneralInfoModel? = null,
    @SerializedName("events") val events : GeneralInfoModel? = null,
    @SerializedName("urls") val urls : List<SuperHeroUrlsModel>? = null

)

data class SuperHeroThumbnailModel(
    @SerializedName("path") val path : String? = null,
    @SerializedName("extension") val extension : String? = null
)