package com.abrebo.nbadatabase.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Teams(
    @SerializedName("Atlanta Hawks") val atlantaHawks: List<Player>,
    @SerializedName("Boston Celtics") val bostonCeltics: List<Player>,
    @SerializedName("Brooklyn Nets") val brooklynNets: List<Player>,
    @SerializedName("Charlotte Hornets") val charlotteHornets: List<Player>,
    @SerializedName("Chicago Bulls") val chicagoBulls: List<Player>,
    @SerializedName("Cleveland Cavaliers") val clevelandCavaliers: List<Player>,//
    @SerializedName("Dallas Mavericks") val dallasMavericks: List<Player>,
    @SerializedName("Denver Nuggets") val denverNuggets: List<Player>,
    @SerializedName("Detroit Pistons") val detroitPistons: List<Player>,
    @SerializedName("Golden State Warriors") val goldenStateWarriors: List<Player>,
    @SerializedName("Houston Rockets") val houstonRockets: List<Player>,
    @SerializedName("Indiana Pacers") val indianaPacers: List<Player>,
    @SerializedName("Los Angeles Clippers") val losAngelesClippers: List<Player>,
    @SerializedName("Los Angeles Lakers") val losAngelesLakers: List<Player>,
    @SerializedName("Memphis Grizzlies") val memphisGrizzlies: List<Player>,
    @SerializedName("Miami Heat") val miamiHeat: List<Player>,
    @SerializedName("Milwaukee Bucks") val milwaukeeBucks: List<Player>,
    @SerializedName("Minnesota Timberwolves") val minnesotaTimberwolves: List<Player>,
    @SerializedName("New Orleans Pelicans") val newOrleansPelicans: List<Player>,
    @SerializedName("New York Knicks") val newYorkKnicks: List<Player>,
    @SerializedName("Oklahoma City Thunder") val oklahomaCityThunder: List<Player>,
    @SerializedName("Orlando Magic") val orlandoMagic: List<Player>,
    @SerializedName("Philadelphia 76ers") val philadelphia76ers: List<Player>,
    @SerializedName("Phoenix Suns") val phoenixSuns: List<Player>,
    @SerializedName("Portland Trail Blazers") val portlandTrailBlazers: List<Player>,
    @SerializedName("Sacramento Kings") val sacramentoKings: List<Player>,
    @SerializedName("San Antonio Spurs") val sanAntonioSpurs: List<Player>,
    @SerializedName("Toronto Raptors") val torontoRaptors: List<Player>,
    @SerializedName("Utah Jazz") val utahJazz: List<Player>,
    @SerializedName("Washington Wizards") val washingtonWizards: List<Player>
):Serializable
