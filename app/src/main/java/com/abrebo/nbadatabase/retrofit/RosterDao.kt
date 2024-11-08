package com.abrebo.nbadatabase.retrofit

import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.data.model.Teams
import retrofit2.http.GET

interface RosterDao {
    @GET("roster.json")
    suspend fun getRosterWithTeams():List<Player>

    @GET("roster_no_team.json")
    suspend fun getRoster():List<Teams>
}