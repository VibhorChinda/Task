package com.example.miskaaandroidtask.api

import com.example.miskaaandroidtask.models.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("/rest/v2/region/asia")
    suspend fun getCountry() : Response<List<Country>>
}