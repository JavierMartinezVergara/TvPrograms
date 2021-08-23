package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName

data class Rating (

	@SerializedName("average") val average : String?
)