package com.example.tvprograms.data.remote

import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.data.remote.responses.ShowResponse
import com.example.tvprograms.data.remote.responses.Talent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProgramsApi {

    @GET("schedule")
    suspend fun getPrograms(
        @Query("country") country : String,
        @Query("date") date : String
    ) : Response<List<Programs>>

    @GET("shows/{id}/cast")
    suspend fun getTalent(
        @Path("id") id : String,
    ) : Response<List<Talent>>

    @GET("shows/{id}")
    suspend fun getDetailProgram(
        @Path("id") id : String
    ) : DetailProgram

    @GET("shows")
    suspend fun getShows(
        @Query("q") query : String
    ) : Response<List<DetailProgram>>

}