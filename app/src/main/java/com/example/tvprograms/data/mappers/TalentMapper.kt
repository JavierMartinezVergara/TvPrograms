package com.example.tvprograms.data.mappers

import com.example.tvprograms.data.local.ProgramsLocal
import com.example.tvprograms.data.local.TalentLocal
import com.example.tvprograms.data.local.Type
import com.example.tvprograms.data.remote.responses.DetailProgram
import com.example.tvprograms.data.remote.responses.ShowResponse
import com.example.tvprograms.data.remote.responses.Talent

class TalentMapper: Mapper<List<Talent>, List<TalentLocal>> {
    override suspend fun map(input: List<Talent>): List<TalentLocal> {
        val listTalent: MutableList<TalentLocal> = mutableListOf()
        input.forEach {
            listTalent.add(
                TalentLocal(
                    name = it.person.name,
                    image = it.person.image.medium,
                ))
        }
        return listTalent
    }
}