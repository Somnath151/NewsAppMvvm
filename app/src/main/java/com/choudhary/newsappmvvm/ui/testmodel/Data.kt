package com.choudhary.newsappmvvm.ui.testmodel

data class Data(
    val bbbEnabled: Boolean,
    val date: String,
    val dateTimeGMT: String,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchType: String,
    val name: String,
    val score: List<Score>,
    val series_id: String,
    val status: String,
    val teamInfo: List<TeamInfo>,
    val teams: List<String>,
    val venue: String
)