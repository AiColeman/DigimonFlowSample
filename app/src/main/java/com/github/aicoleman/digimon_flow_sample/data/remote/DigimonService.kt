package com.github.aicoleman.digimon_flow_sample.data.remote

import com.github.aicoleman.digimon_flow_sample.data.model.Digimon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DigimonService {

    @GET("digimon")
    suspend fun fetchDigimonList(

    ): Response<List<Digimon>>

    @GET("digimon/name/{name}")
    suspend fun fetchDigimonInfo(
        @Path("name") name: String
    ): Response<Digimon>

    @GET("digimon/level/{level}")
    suspend fun fetchDigimonListByLevel(
        @Path("level") level: String
    ): Response<List<Digimon>>

    companion object {
        const val URL = "https://digimon-api.vercel.app/api/"
    }

}