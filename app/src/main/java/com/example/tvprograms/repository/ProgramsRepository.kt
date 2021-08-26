package com.example.tvprograms.repository

import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.local.TalentLocal
import com.example.tvprograms.data.mappers.ProgramsDateMapper
import com.example.tvprograms.data.mappers.ProgramsMapper
import com.example.tvprograms.data.mappers.TalentMapper
import com.example.tvprograms.data.remote.ApiManagerFactory
import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.Talent
import com.example.tvprograms.utils.Resource
import retrofit2.HttpException

class ProgramsRepository {
    private val service = ApiManagerFactory.makeRetrofitService()

    suspend fun getProgramsList(country: String , date : String) : Resource<List<ProgramsLocal>> {
        return try {
            val response = service.getPrograms(country, date)
            if(response.isSuccessful){
                val mapper = ProgramsDateMapper().map(response.body()!!)
                return Resource.Success(mapper)
            } else {
                throw HttpException(response)
            }
        } catch (e : Exception){
            Resource.Error(message = "Algo Salio Mal")
        }
    }

    suspend fun getDetailPrograma(id : String) : Resource<DetailProgram> {
        val response = try {
            service.getDetailProgram(id)
        } catch (e: Exception){
            return Resource.Error(message = "Un error ha ocurrido")
        }
        return Resource.Success(response)
    }

    suspend fun getShowsList(query : String) : Resource<List<ProgramsLocal>> {
        return try {
            val response = service.getShows(query)
            if(response.isSuccessful){
                val mapper = ProgramsMapper().map(response.body()!!)
                return Resource.Success(mapper)
            } else {
                throw HttpException(response)
            }
        } catch (e : Exception){
            Resource.Error(message = "Algo Salio Mal")
        }
    }

    suspend fun getTalent(id : String) : Resource<List<TalentLocal>> {
        return try {
            val response = service.getTalent(id)
            if(response.isSuccessful){
                val mapper = TalentMapper().map(response.body()!!)
                return Resource.Success(mapper)
            } else {
                throw HttpException(response)
            }
        } catch (e : Exception){
            Resource.Error(message = "Algo Salio Mal")
        }
    }

}