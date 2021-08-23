package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName


data class Country (

	@SerializedName("name") val name : String,
	@SerializedName("code") val code : String,
	@SerializedName("timezone") val timezone : String
)