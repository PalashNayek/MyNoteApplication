package com.dac.mynoteapplication.api

import com.dac.mynoteapplication.models.NoteRequest
import com.dac.mynoteapplication.models.NoteResponse
import retrofit2.Response
import retrofit2.http.*

interface NoteAPI {

    @GET("/note")
    suspend fun getNotes(): Response<List<NoteResponse>>

    @POST("/note")
    suspend fun createNote(@Body noteRequest: NoteRequest) : Response<NoteResponse>

    @PUT("/note/{noteId}")
    suspend fun updateNote(@Path("noteId") noteId : String, @Body noteRequest: NoteRequest) : Response<NoteResponse>

    @DELETE("/note/{noteId}")
    suspend fun deleteNote(@Path("noteId") noteId: String) : Response<NoteResponse>
}