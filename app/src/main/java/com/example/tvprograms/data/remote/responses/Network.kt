package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName

data class Network (

	@SerializedName("id") val id : Int?,
	@SerializedName("name") val name : String?,
	@SerializedName("country") val country : Country?
)