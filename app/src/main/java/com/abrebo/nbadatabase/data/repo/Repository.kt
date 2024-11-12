package com.abrebo.nbadatabase.data.repo

import android.content.Context
import com.abrebo.nbadatabase.data.datasource.DataSource
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.data.model.TeamStats
import com.abrebo.nbadatabase.data.model.Teams
import org.json.JSONArray
import org.json.JSONObject

class Repository(private var dataSource: DataSource,
                 private var context: Context) {

    suspend fun getRosterWithTeams():List<Teams> = dataSource.getRosterWithTeams()
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
    fun parsePlayerJson(jsonString: String): List<Player> {
        val playerList = mutableListOf<Player>()
        try {
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val playerJson = jsonArray.getJSONObject(i)
                val player = Player(
                    name = playerJson.getString("name"),
                    team = playerJson.getString("team"),
                    overallAttribute = playerJson.getInt("overallAttribute"),
                    image_url =playerJson.getString("image_url"),
                    position =playerJson.getString("position"),
                    birthdate =playerJson.getString("birthdate"),
                    archetype =playerJson.getString("archetype"),
                    closeShot = playerJson.getInt("closeShot"),
                    midRangeShot = playerJson.getInt("midRangeShot"),
                    threePointShot = playerJson.getInt("threePointShot"),
                    freeThrow = playerJson.getInt("freeThrow"),
                    shotIQ = playerJson.getInt("shotIQ"),
                    offensiveConsistency = playerJson.getInt("offensiveConsistency"),
                    layup = playerJson.getInt("layup"),
                    standingDunk = playerJson.getInt("standingDunk"),
                    drivingDunk = playerJson.getInt("drivingDunk"),
                    postHook = playerJson.getInt("postHook"),
                    postFade = playerJson.getInt("postFade"),
                    postControl = playerJson.getInt("postControl"),
                    drawFoul = playerJson.getInt("drawFoul"),
                    hands = playerJson.getInt("hands"),
                    interiorDefense = playerJson.getInt("interiorDefense"),
                    perimeterDefense = playerJson.getInt("perimeterDefense"),
                    steal = playerJson.getInt("steal"),
                    block = playerJson.getInt("block"),
                    helpDefenseIQ = playerJson.getInt("helpDefenseIQ"),
                    passPerception = playerJson.getInt("passPerception"),
                    defensiveConsistency = playerJson.getInt("defensiveConsistency"),
                    speed = playerJson.getInt("speed"),
                    agility = playerJson.getInt("agility"),
                    strength = playerJson.getInt("strength"),
                    vertical = playerJson.getInt("vertical"),
                    stamina = playerJson.getInt("stamina"),
                    hustle = playerJson.getInt("hustle"),
                    overallDurability = playerJson.getInt("overallDurability"),
                    passAccuracy = playerJson.getInt("passAccuracy"),
                    ballHandle = playerJson.getInt("ballHandle"),
                    speedWithBall = playerJson.getInt("speedWithBall"),
                    passIQ = playerJson.getInt("passIQ"),
                    passVision = playerJson.getInt("passVision"),
                    offensiveRebound = playerJson.getInt("offensiveRebound"),
                    defensiveRebound = playerJson.getInt("defensiveRebound"),
                    id = playerJson.getInt("id")
                )
                playerList.add(player)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return playerList
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
    fun parseTeamsJson(jsonString: String): Teams? {
        return try {
            val jsonObject = JSONObject(jsonString)

            Teams(
                atlantaHawks = parsePlayers(jsonObject.getJSONArray("Atlanta Hawks")),
                bostonCeltics = parsePlayers(jsonObject.getJSONArray("Boston Celtics")),
                brooklynNets = parsePlayers(jsonObject.getJSONArray("Brooklyn Nets")),
                charlotteHornets = parsePlayers(jsonObject.getJSONArray("Charlotte Hornets")),
                chicagoBulls = parsePlayers(jsonObject.getJSONArray("Chicago Bulls")),
                clevelandCavaliers = parsePlayers(jsonObject.getJSONArray("Cleveland Cavaliers")),
                dallasMavericks = parsePlayers(jsonObject.getJSONArray("Dallas Mavericks")),
                denverNuggets = parsePlayers(jsonObject.getJSONArray("Denver Nuggets")),
                detroitPistons = parsePlayers(jsonObject.getJSONArray("Detroit Pistons")),
                goldenStateWarriors = parsePlayers(jsonObject.getJSONArray("Golden State Warriors")),
                houstonRockets = parsePlayers(jsonObject.getJSONArray("Houston Rockets")),
                indianaPacers = parsePlayers(jsonObject.getJSONArray("Indiana Pacers")),
                losAngelesClippers = parsePlayers(jsonObject.getJSONArray("Los Angeles Clippers")),
                losAngelesLakers = parsePlayers(jsonObject.getJSONArray("Los Angeles Lakers")),
                memphisGrizzlies = parsePlayers(jsonObject.getJSONArray("Memphis Grizzlies")),
                miamiHeat = parsePlayers(jsonObject.getJSONArray("Miami Heat")),
                milwaukeeBucks = parsePlayers(jsonObject.getJSONArray("Milwaukee Bucks")),
                minnesotaTimberwolves = parsePlayers(jsonObject.getJSONArray("Minnesota Timberwolves")),
                newOrleansPelicans = parsePlayers(jsonObject.getJSONArray("New Orleans Pelicans")),
                newYorkKnicks = parsePlayers(jsonObject.getJSONArray("New York Knicks")),
                oklahomaCityThunder = parsePlayers(jsonObject.getJSONArray("Oklahoma City Thunder")),
                orlandoMagic = parsePlayers(jsonObject.getJSONArray("Orlando Magic")),
                philadelphia76ers = parsePlayers(jsonObject.getJSONArray("Philadelphia 76ers")),
                phoenixSuns = parsePlayers(jsonObject.getJSONArray("Phoenix Suns")),
                portlandTrailBlazers = parsePlayers(jsonObject.getJSONArray("Portland Trail Blazers")),
                sacramentoKings = parsePlayers(jsonObject.getJSONArray("Sacramento Kings")),
                sanAntonioSpurs = parsePlayers(jsonObject.getJSONArray("San Antonio Spurs")),
                torontoRaptors = parsePlayers(jsonObject.getJSONArray("Toronto Raptors")),
                utahJazz = parsePlayers(jsonObject.getJSONArray("Utah Jazz")),
                washingtonWizards = parsePlayers(jsonObject.getJSONArray("Washington Wizards"))
            )

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    private fun parsePlayers(jsonArray: JSONArray): List<Player> {
        val playerList = mutableListOf<Player>()
        for (i in 0 until jsonArray.length()) {
            val playerJson = jsonArray.getJSONObject(i)
            val player = Player(
                name = playerJson.getString("name"),
                team = playerJson.getString("team"),
                overallAttribute = playerJson.getInt("overallAttribute"),
                image_url = playerJson.getString("image_url"),
                position =playerJson.getString("position"),
                birthdate =playerJson.getString("birthdate"),
                archetype =playerJson.getString("archetype"),
                closeShot = playerJson.getInt("closeShot"),
                midRangeShot = playerJson.getInt("midRangeShot"),
                threePointShot = playerJson.getInt("threePointShot"),
                freeThrow = playerJson.getInt("freeThrow"),
                shotIQ = playerJson.getInt("shotIQ"),
                offensiveConsistency = playerJson.getInt("offensiveConsistency"),
                layup = playerJson.getInt("layup"),
                standingDunk = playerJson.getInt("standingDunk"),
                drivingDunk = playerJson.getInt("drivingDunk"),
                postHook = playerJson.getInt("postHook"),
                postFade = playerJson.getInt("postFade"),
                postControl = playerJson.getInt("postControl"),
                drawFoul = playerJson.getInt("drawFoul"),
                hands = playerJson.getInt("hands"),
                interiorDefense = playerJson.getInt("interiorDefense"),
                perimeterDefense = playerJson.getInt("perimeterDefense"),
                steal = playerJson.getInt("steal"),
                block = playerJson.getInt("block"),
                helpDefenseIQ = playerJson.getInt("helpDefenseIQ"),
                passPerception = playerJson.getInt("passPerception"),
                defensiveConsistency = playerJson.getInt("defensiveConsistency"),
                speed = playerJson.getInt("speed"),
                agility = playerJson.getInt("agility"),
                strength = playerJson.getInt("strength"),
                vertical = playerJson.getInt("vertical"),
                stamina = playerJson.getInt("stamina"),
                hustle = playerJson.getInt("hustle"),
                overallDurability = playerJson.getInt("overallDurability"),
                passAccuracy = playerJson.getInt("passAccuracy"),
                ballHandle = playerJson.getInt("ballHandle"),
                speedWithBall = playerJson.getInt("speedWithBall"),
                passIQ = playerJson.getInt("passIQ"),
                passVision = playerJson.getInt("passVision"),
                offensiveRebound = playerJson.getInt("offensiveRebound"),
                defensiveRebound = playerJson.getInt("defensiveRebound"),
                id = playerJson.getInt("id")
            )
            playerList.add(player)
        }
        return playerList
    }
}