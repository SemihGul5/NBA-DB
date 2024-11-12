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
import com.abrebo.nbadatabase.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterAndSortViewModel @Inject constructor (var repository: Repository,
                                                  application: Application): AndroidViewModel(application){
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    val players= MutableLiveData<List<Player>>()
    fun filterAndSortPlayers(s: String) {
        viewModelScope.launch {
            val parsedPlayers = repository.getRoster()
            players.value = when (s) {
                    "overallAttribute" -> parsedPlayers.sortedByDescending { it.overallAttribute }
                    "closeShot" -> parsedPlayers.sortedByDescending { it.closeShot }
                    "midRangeShot" -> parsedPlayers.sortedByDescending { it.midRangeShot }
                    "threePointShot" -> parsedPlayers.sortedByDescending { it.threePointShot }
                    "freeThrow" -> parsedPlayers.sortedByDescending { it.freeThrow }
                    "shotIQ" -> parsedPlayers.sortedByDescending { it.shotIQ }
                    "offensiveConsistency" -> parsedPlayers.sortedByDescending { it.offensiveConsistency }
                    "layup" -> parsedPlayers.sortedByDescending { it.layup }
                    "standingDunk" -> parsedPlayers.sortedByDescending { it.standingDunk }
                    "drivingDunk" -> parsedPlayers.sortedByDescending { it.drivingDunk }
                    "postHook" -> parsedPlayers.sortedByDescending { it.postHook }
                    "postFade" -> parsedPlayers.sortedByDescending { it.postFade }
                    "postControl" -> parsedPlayers.sortedByDescending { it.postControl }
                    "drawFoul" -> parsedPlayers.sortedByDescending { it.drawFoul }
                    "hands" -> parsedPlayers.sortedByDescending { it.hands }
                    "interiorDefense" -> parsedPlayers.sortedByDescending { it.interiorDefense }
                    "perimeterDefense" -> parsedPlayers.sortedByDescending { it.perimeterDefense }
                    "steal" -> parsedPlayers.sortedByDescending { it.steal }
                    "block" -> parsedPlayers.sortedByDescending { it.block }
                    "helpDefenseIQ" -> parsedPlayers.sortedByDescending { it.helpDefenseIQ }
                    "passPerception" -> parsedPlayers.sortedByDescending { it.passPerception }
                    "defensiveConsistency" -> parsedPlayers.sortedByDescending { it.defensiveConsistency }
                    "speed" -> parsedPlayers.sortedByDescending { it.speed }
                    "agility" -> parsedPlayers.sortedByDescending { it.agility }
                    "strength" -> parsedPlayers.sortedByDescending { it.strength }
                    "vertical" -> parsedPlayers.sortedByDescending { it.vertical }
                    "stamina" -> parsedPlayers.sortedByDescending { it.stamina }
                    "hustle" -> parsedPlayers.sortedByDescending { it.hustle }
                    "overallDurability" -> parsedPlayers.sortedByDescending { it.overallDurability }
                    "passAccuracy" -> parsedPlayers.sortedByDescending { it.passAccuracy }
                    "ballHandle" -> parsedPlayers.sortedByDescending { it.ballHandle }
                    "speedWithBall" -> parsedPlayers.sortedByDescending { it.speedWithBall }
                    "passIQ" -> parsedPlayers.sortedByDescending { it.passIQ }
                    "passVision" -> parsedPlayers.sortedByDescending { it.passVision }
                    "offensiveRebound" -> parsedPlayers.sortedByDescending { it.offensiveRebound }
                    "defensiveRebound" -> parsedPlayers.sortedByDescending { it.defensiveRebound }
                    else -> parsedPlayers
                }
        }
    }

    fun top100Players() {
        viewModelScope.launch {
            players.value=repository.getRoster().sortedByDescending {it.overallAttribute }.take(100)
        }
    }
    fun sortThreePointPlayers() {
        viewModelScope.launch {
            players.value=repository.getRoster().sortedByDescending {it.threePointShot }.take(100)
        }
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
}