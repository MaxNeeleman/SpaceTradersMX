package com.github.maxneeleman.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseAgent(
    @SerialName("data")
    val agent: Agent
)

@Serializable
data class Agent(
    val accountId: String,
    val symbol: String,
    val headquarters: String,
    val credits: Int,
    val startingFaction : String,
    val shipCount : Int
)