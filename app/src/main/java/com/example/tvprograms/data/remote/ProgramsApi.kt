package com.example.tvprograms.data.remote

import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.Programs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProgramsApi {

    @GET("schedule")
    suspend fun getPrograms(
        @Query("country") country : String,
        @Query("date") date : String
    ) : List<Programs>

    @GET("shows/{id}")
    suspend fun getDetailProgram(
        @Path("id") id : String
    ) : DetailProgram

}