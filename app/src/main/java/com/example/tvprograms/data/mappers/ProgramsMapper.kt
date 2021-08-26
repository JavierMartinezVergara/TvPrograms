package com.example.tvprograms.data.mappers

import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.local.Type
import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.ShowResponse

class ProgramsMapper: Mapper<List<DetailProgram>, List<ProgramsLocal>> {
    override suspend fun map(input: List<DetailProgram>): List<ProgramsLocal> {
        val listProgrmasDomainModel: MutableList<ProgramsLocal> = mutableListOf()
        input.forEach {
            listProgrmasDomainModel.add(
                ProgramsLocal(
                    id = it.id,
                    url = it.url,
                    name = it.name,
                    airdate = it.schedule.time,
                    airtime = it.schedule.days.toString(),
                    image = it.image.medium,
                    type = Type.SHOW.name
                ))
        }
        return listProgrmasDomainModel
    }
}