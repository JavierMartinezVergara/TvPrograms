package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName


data class Character (

	@SerializedName("id") val id : Int,
	@SerializedName("url") val url : String,
	@SerializedName("name") val name : String,
	@SerializedName("image") val image : Image?,
	@SerializedName("_links") val _links : Links?
)