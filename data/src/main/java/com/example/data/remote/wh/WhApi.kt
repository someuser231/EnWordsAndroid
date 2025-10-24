package com.example.data.remote.wh

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface WhApi {
    @GET("whapi/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): WhResponse
}