package com.example.tvprograms.repository

import com.example.tvprograms.data.remote.ApiManagerFactory
import com.example.tvprograms.data.remote.responses.Country
import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.utils.Resource
import java.lang.Exception
import java.util.*

class ProgramsRepository {
    private val service = ApiManagerFactory.makeRetrofitService()

    suspend fun getProgramsList(country: String , date : String) : Resource<List<Programs>> {
        val response = try {
            service.getPrograms(country, date)
        } catch (e: Exception){
            return Resource.Error(message = "Un error ha ocurrido")
        }
        return Resource.Success(response)
    }

    suspend fun getDetailPrograma(id : String) : Resource<DetailProgram> {
        val response = try {
            service.getDetailProgram(id)
        } catch (e: Exception){
            return Resource.Error(message = "Un error ha ocurrido")
        }
        return Resource.Success(response)
    }

}