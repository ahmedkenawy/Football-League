package com.ahmedkenawy.footballleague.core.mapper

interface Mapper<in I, out O> {
    fun map(model: I): O
}