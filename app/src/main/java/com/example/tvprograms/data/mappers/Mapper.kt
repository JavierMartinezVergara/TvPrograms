package com.example.tvprograms.data.mappers

interface Mapper<I, O> {
    suspend fun map(input: I): O
}