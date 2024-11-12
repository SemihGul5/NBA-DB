package com.abrebo.nbadatabase.data.repo

import android.content.Context
import com.abrebo.nbadatabase.data.datasource.DataSource
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.data.model.TeamStats
import org.json.JSONArray
import org.json.JSONObject

class Repository(private var dataSource: DataSource,
                 private var context: Context) {

    suspend fun getRoster():List<Player> = dataSource.getRoster()
    fun loadJsonFromAsset(jsonFile:String): String? {
        val jsonString: String
        try {
            val inputStream = context.resources.openRawResource(
                context.resources.getIdentifier(jsonFile, "raw", context.packageName)
            )
            jsonString = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
    fun parseTeamStatsJson(jsonString: String): List<TeamStats> {
        val teamStatsList = mutableListOf<TeamStats>()
        try {
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val teamStatsJson = jsonArray.getJSONObject(i)
                val teamStats = TeamStats(
                    team_name = teamStatsJson.getString("team_name"),
                    tier = teamStatsJson.getString("tier"),
                    ovr = teamStatsJson.getString("ovr"),
                    ins = teamStatsJson.getString("ins"),
                    out = teamStatsJson.getString("out"),
                    ath = teamStatsJson.getString("ath"),
                    pla = teamStatsJson.getString("pla"),
                    def = teamStatsJson.getString("def"),
                    reb = teamStatsJson.getString("reb"),
                    int = teamStatsJson.getString("int"),
                    pot = teamStatsJson.getString("pot"),
                )
                teamStatsList.add(teamStats)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return teamStatsList
    }

}