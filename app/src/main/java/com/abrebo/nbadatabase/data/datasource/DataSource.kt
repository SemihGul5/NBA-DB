package com.abrebo.nbadatabase.data.datasource

import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.data.model.Teams
import com.abrebo.nbadatabase.retrofit.RosterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSource(private val rosterDao: RosterDao) {

    suspend fun getRosterWithTeams():List<Teams> =
        withContext(Dispatchers.IO){
            return@withContext rosterDao.getRosterWithTeams()
        }

    suspend fun getRoster():List<Player> =
        withContext(Dispatchers.IO){
            return@withContext rosterDao.getRoster()
        }

}