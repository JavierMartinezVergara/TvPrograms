package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName



data class Person (

	@SerializedName("id") val id : Int,
	@SerializedName("url") val url : String,
	@SerializedName("name") val name : String,
	@SerializedName("country") val country : Country?,
	@SerializedName("birthday") val birthday : String?,
	@SerializedName("deathday") val deathday : String?,
	@SerializedName("gender") val gender : String,
	@SerializedName("image") val image : Image,
	@SerializedName("_links") val _links : Links
)