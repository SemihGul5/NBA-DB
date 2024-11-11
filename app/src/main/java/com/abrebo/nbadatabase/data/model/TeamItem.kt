package com.abrebo.nbadatabase.data.model

import java.io.Serializable

data class TeamItem(
    val teamName: String,
    val logoRes: Int,
    val backgroundRes: Int,
    val tier: String,
    val ovr: Int,
    val ins: Int,
    val out: Int,
    val ath: Int,
    val pla: Int,
    val def: Int,
    val reb: Int,
    val int: Int,
    val pot: Int
):Serializable
