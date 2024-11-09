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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
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

    init {
        loadPlayersFromAsset()
        loadTeamsFromAsset()
        loadTeamStatsFromAsset()
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
    private fun loadTeamsFromAsset() {
        teamsAsset.value =repository.parseTeamsJson("roster")
    }
    private fun loadTeamStatsFromAsset(){
        val jsonString =repository.loadJsonFromAsset("team_stats")
        teamStats.value=repository.parseTeamStatsJson(jsonString!!)
    }
    private fun getImageResourceByName(imageName: String): Int {
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
    fun loadTeamItems():List<TeamItem>{
        return listOf(
            TeamItem("Atlanta Hawks",R.drawable.atlanta_hawks_logo,R.drawable.card_background_atlanta_hawks),
            TeamItem("Boston Celtics",R.drawable.boston_celtics_logo,R.drawable.card_background_boston_celtics),
            TeamItem("Brooklyn Nets",R.drawable.brooklyn_nets_logo,R.drawable.card_background_brooklyn_nets),
            TeamItem("Charlotte Hornets",R.drawable.charlotte_hornets_logo,R.drawable.card_background_charlotte_hornets),
            TeamItem("Chicago Bulls",R.drawable.chicago_bulls_logo,R.drawable.card_background_chicago_bulls),
            TeamItem("Cleveland Cavaliers",R.drawable.cleveland_cavaliers_logo,R.drawable.card_background_cleveland_cavaliers),
            TeamItem("Dallas Mavericks",R.drawable.dallas_mavericks_logo,R.drawable.card_background_dallas_mavericks),
            TeamItem("Denver Nuggets",R.drawable.denver_nuggets_logo,R.drawable.card_background_denver_nuggets),
            TeamItem("Detroit Pistons",R.drawable.detroit_pistons_logo,R.drawable.card_background_detroit_pistons),
            TeamItem("Golden State Warriors",R.drawable.golden_state_warriors_logo,R.drawable.card_background_golden_state_warriors),
            TeamItem("Houston Rockets",R.drawable.houston_rockets_logo,R.drawable.card_background_houston_rockets),
            TeamItem("Indiana Pacers",R.drawable.indiana_pacers_logo,R.drawable.card_background_indiana_pacers),
            TeamItem("Los Angeles Clippers",R.drawable.los_angeles_clippers_logo,R.drawable.card_background_los_angeles_clippers),
            TeamItem("Los Angeles Lakers",R.drawable.los_angeles_lakers_logo,R.drawable.card_background_los_angeles_lakers),
            TeamItem("Memphis Grizzlies",R.drawable.memphis_grizzlies_logo,R.drawable.card_background_memphis_grizzlies),
            TeamItem("Miami Heat",R.drawable.miami_heat_logo,R.drawable.card_background_miami_heat),
            TeamItem("Milwaukee Bucks",R.drawable.milwaukee_bucks_logo,R.drawable.card_background_milwaukee_bucks),
            TeamItem("Minnesota Timberwolves",R.drawable.minnesota_timberwolves_logo,R.drawable.card_background_minnesota_timberwolves),
            TeamItem("New Orleans Pelicans",R.drawable.new_orleans_pelicans_logo,R.drawable.card_background_new_orleans_pelicans),
            TeamItem("New York Knicks",R.drawable.new_york_knicks_logo,R.drawable.card_background_new_york_knicks),
            TeamItem("Oklahoma City Thunder",R.drawable.oklahoma_city_thunder_logo,R.drawable.card_background_oklahoma_city_thunder),
            TeamItem("Orlando Magic",R.drawable.orlando_magic_logo,R.drawable.card_background_orlando_magic),
            TeamItem("Philadelphia 76ers",R.drawable.philadelphia_ers_logo,R.drawable.card_background_philadelphia_76ers),
            TeamItem("Phoenix Suns",R.drawable.phoenix_suns_logo,R.drawable.card_background_phoenix_suns),
            TeamItem("Portland Trail Blazers",R.drawable.portland_trail_blazers_logo,R.drawable.card_background_portland_trail_blazers),
            TeamItem("Sacramento Kings",R.drawable.sacramentokings_logo,R.drawable.card_background_sacramento_kings),
            TeamItem("San Antonio Spurs",R.drawable.san_antonio_spurs_logo,R.drawable.card_background_san_antonio_spurs),
            TeamItem("Toronto Raptors",R.drawable.toronto_raptors_logo,R.drawable.card_background_toronto_raptors),
            TeamItem("Utah Jazz",R.drawable.utah_jazz_logo,R.drawable.card_background_utah_jazz),
            TeamItem("Washington Wizards",R.drawable.washington_wizards_logo,R.drawable.card_background_washington_wizards)

        )


    }






}