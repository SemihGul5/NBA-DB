package com.abrebo.nbadatabase.retrofit

import com.abrebo.nbadatabase.data.model.Player
import retrofit2.http.GET

interface RosterDao {

    @GET("roster_new.json")
    suspend fun getRoster():List<Player>
}