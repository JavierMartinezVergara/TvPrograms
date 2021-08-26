package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName

data class ShowResponse (

	@SerializedName("score") val score : Double,
	@SerializedName("show") val show : Show
)