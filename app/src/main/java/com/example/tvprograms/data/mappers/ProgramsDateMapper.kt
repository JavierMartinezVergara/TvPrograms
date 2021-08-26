package com.example.tvprograms.data.mappers

import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.Programs
import com.example.tvprograms.data.local.Type
import com.example.tvprograms.data.remote.responses.ShowResponse

class ProgramsDateMapper: Mapper<List<Programs>, List<ProgramsLocal>> {
    override suspend fun map(input: List<Programs>): List<ProgramsLocal> {
        val listProgrmasDomainModel: MutableList<ProgramsLocal> = mutableListOf()
        input.forEach {
            listProgrmasDomainModel.add(
                ProgramsLocal(
                    id = it.id,
                    url = it.url,
                    name = it.name,
                    airdate = it.show.schedule?.time,
                    airtime = it.show.schedule?.days.toString(),
                    image = it.image?.medium,
                    type = Type.PROGRAM.name
                ))
        }
        return listProgrmasDomainModel
    }
}