package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName

data class Externals (

	@SerializedName("tvrage") val tvrage : String?,
	@SerializedName("thetvdb") val thetvdb : Int?,
	@SerializedName("imdb") val imdb : String?
)