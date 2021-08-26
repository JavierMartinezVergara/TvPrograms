package com.example.tvprograms.data.local
import com.google.gson.annotations.SerializedName

data class ProgramsLocal (

	val id : Int,
	val url : String,
	val name : String,
	val image : String?,
	val airdate : String?,
	val airtime : String?,
	val type : String?
)

enum class Type{
	PROGRAM,
	SHOW
}