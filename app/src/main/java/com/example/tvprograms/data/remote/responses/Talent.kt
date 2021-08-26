package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName


data class Talent (

	@SerializedName("person") val person : Person,
	@SerializedName("character") val character : Character,
	@SerializedName("self") val self : Boolean,
	@SerializedName("voice") val voice : Boolean
)