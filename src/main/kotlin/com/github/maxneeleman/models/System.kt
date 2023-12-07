package com.github.maxneeleman.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseSystem(
    @SerialName("data")
    val systems: List<System>,
    val meta: SystemMeta
)

@Serializable
data class ApiResponseSingleSystem(
    @SerialName("data")
    val system: System
)

@Serializable
data class System(
    val symbol: String,
    val sectorSymbol: String,
    val type: String,
    val x: Int,
    val y: Int,
    val waypoints: List<Waypoint>,
    val factions: List<Faction>
)

@Serializable
data class Waypoint(
    val symbol: String,
    val type: String,
    val x: Int,
    val y: Int,
    val orbitals: List<Orbital>,
    val orbits: String? = null
)

@Serializable
data class Orbital(
    val symbol: String
)

@Serializable
data class Faction(
    val symbol: String
)

@Serializable
data class SystemMeta(
    val total: Int,
    val page: Int,
    val limit: Int
)