package com.example.tvprograms.data.remote.responses
import com.google.gson.annotations.SerializedName

data class Schedule (

	@SerializedName("time") val time : String,
	@SerializedName("days") val days : List<String>
)