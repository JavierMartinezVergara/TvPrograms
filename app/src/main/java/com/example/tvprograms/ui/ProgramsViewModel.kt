package com.example.tvprograms.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.local.TalentLocal
import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.repository.ProgramsRepository
import com.example.tvprograms.utils.Resource
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ProgramsViewModel : ViewModel() {

    private val repository by lazy {
        ProgramsRepository()
    }

    val programsLiveData = MutableLiveData<List<ProgramsLocal>>()
    val programsDetailData = MutableLiveData<DetailProgram>()
    val programsShowListData = MutableLiveData<List<ProgramsLocal>>()
    val talentsLiveData = MutableLiveData<List<TalentLocal>>()
    var loading = MutableLiveData(false)
    var error = MutableLiveData("")

    init {
        val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
        getPrograms("US", date)
    }


    fun getPrograms(country: String, date: String){
        loading.value = true
        viewModelScope.launch {
            val response = repository.getProgramsList(country, date)
            when(response){
                is Resource.Success -> {
                    loading.value = false
                    error.value = ""
                    programsLiveData.value = response.data!!
                }
                is Resource.Error -> {
                    loading.value = false
                    error.value = response.message
                }
            }
        }
    }

    fun getProgramDetail(id: String){
        loading.value = true
        viewModelScope.launch {
            val response = repository.getDetailPrograma(id)
            when(response){
                is Resource.Success -> {
                    loading.value = false
                    error.value = ""
                    programsDetailData.value = response.data!!
                }
                is Resource.Error -> {
                    loading.value = false
                    error.value = response.message
                    //programsDetailData.value =
                }
                else -> {}
            }
        }
    }

    fun getShowList(query: String){
        loading.value = true
        viewModelScope.launch {
            val response = repository.getShowsList(query)
            when(response){
                is Resource.Success -> {
                    loading.value = false
                    error.value = ""
                    programsShowListData.value = response.data!!
                }
                is Resource.Error -> {
                    loading.value = false
                    error.value = response.message
                }
                else -> {}
            }
        }
    }


    fun getTalents(id: String){
        loading.value = true
        viewModelScope.launch {
            val response = repository.getTalent(id)
            when(response){
                is Resource.Success -> {
                    loading.value = false
                    error.value = ""
                    talentsLiveData.value = response.data!!
                }
                is Resource.Error -> {
                    loading.value = false
                    error.value = response.message
                }
                else -> {}
            }
        }
    }

    fun delete(){
        programsLiveData.value = mutableListOf()
    }


}