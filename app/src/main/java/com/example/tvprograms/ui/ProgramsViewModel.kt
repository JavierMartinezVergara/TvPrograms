package com.example.tvprograms.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvprograms.data.remote.responses.Country
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.repository.ProgramsRepository
import com.example.tvprograms.utils.Resource
import kotlinx.coroutines.launch
import java.util.*

class ProgramsViewModel : ViewModel() {

    private val repository by lazy {
        ProgramsRepository()
    }

    val programsLiveData = MutableLiveData<List<Programs>>()


    fun getPrograms(country: String , date : String){
        viewModelScope.launch {
            val response = repository.getProgramsList(country,date)
            when(response){
                is Resource.Success ->{
                    programsLiveData.value = response.data
                }
                is Resource.Error -> {
                    programsLiveData.value = mutableListOf()
                }
            }
        }
    }
}