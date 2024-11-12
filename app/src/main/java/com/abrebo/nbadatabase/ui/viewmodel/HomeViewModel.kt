package com.abrebo.nbadatabase.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.data.model.TeamItem
import com.abrebo.nbadatabase.data.model.TeamStats
import com.abrebo.nbadatabase.data.model.Teams
import com.abrebo.nbadatabase.data.repo.Repository
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (var repository: Repository,
                                         application: Application): AndroidViewModel(application){
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    val players=MutableLiveData<List<Player>>()
    val teams=MutableLiveData<List<Teams>>()
    val teamsAsset=MutableLiveData<Teams>()
    val teamStats=MutableLiveData<List<TeamStats>>()
    val sortedTeams = MutableLiveData<List<TeamItem>>()

    init {
        loadPlayersFromAsset()
        loadTeamsFromAsset()
        loadTeamStatsFromAsset()
        sortedTeamsFromAsset("Defaault")
    }

    fun getRosterWithTeamsFromApi(){
        viewModelScope.launch {
            players.value=repository.getRosterWithTeams()
        }
    }
    fun getRosterFromApi(){
        viewModelScope.launch {
            teams.value=repository.getRoster()
        }
    }

    private fun loadPlayersFromAsset() {
        val jsonString =repository.loadJsonFromAsset("roster_with_ids")
        if (jsonString != null) {
            players.value = repository.parsePlayerJson(jsonString)
        }
    }
    fun loadTeamsFromAsset() {
        teamsAsset.value =repository.parseTeamsJson("roster")
    }
    fun sortedTeamsFromAsset(sortInfo: String) {
        val sortedList = when (sortInfo) {
            "Default" -> loadTeamItems()
            "Overall" -> loadTeamItems().sortedByDescending { it.ovr }
            "Inside Scoring" -> loadTeamItems().sortedByDescending { it.ins }
            "Outside Scoring" -> loadTeamItems().sortedByDescending { it.out }
            "Athleticism"->loadTeamItems().sortedByDescending { it.ath }
            "Playmaking" -> loadTeamItems().sortedByDescending { it.pla }
            "Defense" -> loadTeamItems().sortedByDescending { it.def }
            "Rebounding" -> loadTeamItems().sortedByDescending { it.reb }
            "Intangibles" -> loadTeamItems().sortedByDescending { it.int }
            "Potential" -> loadTeamItems().sortedByDescending { it.pot }
            else -> loadTeamItems()
        }
        sortedTeams.value = sortedList
    }
    private fun loadTeamStatsFromAsset(){
        val jsonString =repository.loadJsonFromAsset("team_stats")
        teamStats.value=repository.parseTeamStatsJson(jsonString!!)
    }
    fun getImageResourceByName(imageName: String): Int {
        val resourceId = context.resources?.getIdentifier(imageName, "drawable", context.packageName)
        return if (resourceId != 0) {
            resourceId ?: R.drawable.default_image
        } else {
            R.drawable.default_image
        }
        //val imageResource = getImageResourceByName(question.player.imageUrl)
    }
    fun setAttributesBackground(attributeValue: Int, textView: TextView) {
        val backgroundResource = when {
            attributeValue > 85 -> R.drawable.overall_dark_green_background
            attributeValue in 80..85 -> R.drawable.overall_light_green_background
            attributeValue in 75..79 -> R.drawable.overall_dark_yellow_background
            attributeValue in 70..74 -> R.drawable.overall_light_gray_background
            attributeValue in 60..69 -> R.drawable.overall_orange_background
            else -> R.drawable.overall_dark_red_background
        }
        textView.setBackgroundResource(backgroundResource)
    }
    fun updateRadarChart(
        overall: Int,
        defending: Int,
        insideScoring: Int,
        rebounding: Int,
        outsideScoring: Int,
        playmarking: Int,
        athleticism:Int,
        performanceChart:RadarChart
    ) {
        val entries = ArrayList<RadarEntry>()

        entries.add(RadarEntry(overall.toFloat()))//1
        entries.add(RadarEntry(insideScoring.toFloat()))//3
        entries.add(RadarEntry(athleticism.toFloat()))//5
        entries.add(RadarEntry(rebounding.toFloat()))//7
        entries.add(RadarEntry(defending.toFloat()))//6
        entries.add(RadarEntry(playmarking.toFloat()))//4
        entries.add(RadarEntry(outsideScoring.toFloat()))//2

        val dataSet = RadarDataSet(entries, "Performance")
        dataSet.color = R.color.gray
        dataSet.fillColor =  R.color.gray
        dataSet.lineWidth = 1.5f
        dataSet.setDrawFilled(true)
        dataSet.fillAlpha = 80
        dataSet.isDrawHighlightCircleEnabled=true
        dataSet.setDrawHighlightIndicators(false)

        val radarData = RadarData(dataSet)
        performanceChart.data = radarData
        performanceChart.xAxis.textSize = 10f
        performanceChart.yAxis.textSize = 10f
        performanceChart.legend.textSize = 11f


        performanceChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when (value.toInt()) {
                    0 -> "OVERALL"
                    1 -> "Inside Scoring"
                    2 -> "Athleticism"
                    3 -> "Rebounding"
                    4 -> "Defense"
                    5 -> "Playmaking"
                    6 -> "Outside Scoring"
                    else -> ""
                }
            }
        }

        performanceChart.yAxis.axisMaximum = 100f
        performanceChart.yAxis.axisMinimum = 0f
        performanceChart.xAxis.axisMaximum = 100f
        performanceChart.xAxis.axisMinimum = 0f
        performanceChart.yAxis.setDrawLabels(false)
        performanceChart.yAxis.setDrawGridLines(false)
        performanceChart.xAxis.setDrawGridLines(false)
        performanceChart.yAxis.setDrawAxisLine(false)
        performanceChart.xAxis.setDrawAxisLine(false)
        performanceChart.yAxis.setDrawLabels(false)
        performanceChart.yAxis.setDrawGridLines(false)
        performanceChart.xAxis.setDrawGridLines(false)
        performanceChart.legend.isEnabled=false
        performanceChart.animateXY(1000, 1000)
        performanceChart.invalidate()
    }
    fun loadTeamItems(): List<TeamItem> {
        val jsonFileString = repository.loadJsonFromAsset("team_stats")
        val teamStats: List<TeamStats> = repository.parseTeamStatsJson(jsonFileString!!)

        val teamImages = mapOf(
            "Atlanta Hawks" to Pair(R.drawable.atlanta_hawks_logo, R.drawable.card_background_atlanta_hawks),
            "Boston Celtics" to Pair(R.drawable.boston_celtics_logo, R.drawable.card_background_boston_celtics),
            "Brooklyn Nets" to Pair(R.drawable.brooklyn_nets_logo, R.drawable.card_background_brooklyn_nets),
            "Charlotte Hornets" to Pair(R.drawable.charlotte_hornets_logo, R.drawable.card_background_charlotte_hornets),
            "Chicago Bulls" to Pair(R.drawable.chicago_bulls_logo, R.drawable.card_background_chicago_bulls),
            "Cleveland Cavaliers" to Pair(R.drawable.cleveland_cavaliers_logo,R.drawable.card_background_cleveland_cavaliers),
            "Dallas Mavericks" to Pair(R.drawable.dallas_mavericks_logo,R.drawable.card_background_dallas_mavericks),
            "Denver Nuggets" to Pair(R.drawable.denver_nuggets_logo,R.drawable.card_background_denver_nuggets),
            "Detroit Pistons" to Pair(R.drawable.detroit_pistons_logo,R.drawable.card_background_detroit_pistons),
            "Golden State Warriors" to Pair(R.drawable.golden_state_warriors_logo,R.drawable.card_background_golden_state_warriors),
            "Houston Rockets" to Pair(R.drawable.houston_rockets_logo,R.drawable.card_background_houston_rockets),
            "Indiana Pacers" to Pair(R.drawable.indiana_pacers_logo,R.drawable.card_background_indiana_pacers),
            "Los Angeles Clippers" to Pair(R.drawable.los_angeles_clippers_logo,R.drawable.card_background_los_angeles_clippers),
            "Los Angeles Lakers" to Pair(R.drawable.los_angeles_lakers_logo,R.drawable.card_background_los_angeles_lakers),
            "Memphis Grizzlies" to Pair(R.drawable.memphis_grizzlies_logo,R.drawable.card_background_memphis_grizzlies),
            "Miami Heat" to Pair(R.drawable.miami_heat_logo,R.drawable.card_background_miami_heat),
            "Milwaukee Bucks" to Pair(R.drawable.milwaukee_bucks_logo,R.drawable.card_background_milwaukee_bucks),
            "Minnesota Timberwolves" to Pair(R.drawable.minnesota_timberwolves_logo,R.drawable.card_background_minnesota_timberwolves),
            "New Orleans Pelicans" to Pair(R.drawable.new_orleans_pelicans_logo,R.drawable.card_background_new_orleans_pelicans),
            "New York Knicks" to Pair(R.drawable.new_york_knicks_logo,R.drawable.card_background_new_york_knicks),
            "Oklahoma City Thunder" to Pair(R.drawable.oklahoma_city_thunder_logo,R.drawable.card_background_oklahoma_city_thunder),
            "Orlando Magic" to Pair(R.drawable.orlando_magic_logo,R.drawable.card_background_orlando_magic),
            "Philadelphia 76ers" to Pair(R.drawable.philadelphia_ers_logo,R.drawable.card_background_philadelphia_76ers),
            "Phoenix Suns" to Pair(R.drawable.phoenix_suns_logo,R.drawable.card_background_phoenix_suns),
            "Portland Trail Blazers" to Pair(R.drawable.portland_trail_blazers_logo,R.drawable.card_background_portland_trail_blazers),
            "Sacramento Kings" to Pair(R.drawable.sacramentokings_logo,R.drawable.card_background_sacramento_kings),
            "San Antonio Spurs" to Pair(R.drawable.san_antonio_spurs_logo,R.drawable.card_background_san_antonio_spurs),
            "Toronto Raptors" to Pair(R.drawable.toronto_raptors_logo,R.drawable.card_background_toronto_raptors),
            "Utah Jazz" to Pair(R.drawable.utah_jazz_logo,R.drawable.card_background_utah_jazz),
            "Washington Wizards" to Pair(R.drawable.washington_wizards_logo,R.drawable.card_background_washington_wizards)
        )
        return teamStats.map { stat ->
            val (logoRes, backgroundRes) = teamImages[stat.team_name] ?: Pair(0, 0)
            TeamItem(
                teamName = stat.team_name,
                logoRes = logoRes,
                backgroundRes = backgroundRes,
                tier = stat.tier,
                ovr = stat.ovr.toInt(),
                ins = stat.ins.toInt(),
                out = stat.out.toInt(),
                ath = stat.ath.toInt(),
                pla = stat.pla.toInt(),
                def = stat.def.toInt(),
                reb = stat.reb.toInt(),
                int = stat.int.toInt(),
                pot = stat.pot.toInt()
            )
        }
    }
}