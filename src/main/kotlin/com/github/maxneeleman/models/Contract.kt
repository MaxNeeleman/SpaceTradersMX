package com.github.maxneeleman.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Instant

@Serializable
data class ApiResponseContract(
    @SerialName("data")
    val contracts: List<Contract>,
    val meta: ContractMeta
)

@Serializable
data class ContractMeta(
    val total: Int,
    val page: Int,
    val limit: Int
)

@Serializable
data class Contract (
    val id: String,
    val factionSymbol: String,
    val type: String,
    val terms: Terms,
    val accepted: Boolean = false,
    val fulfilled: Boolean = false,
    val expiration: Instant,
    val deadlineToAccept: Instant
)

@Serializable
data class Terms(
    val deadline: Instant,
    val payment: Payment,
    val deliver: List<Deliver>
)

@Serializable
data class Payment(
    val onAccepted: Int,
    val onFulfilled: Int
)

@Serializable
data class Deliver(
    val tradeSymbol: String,
    val destinationSymbol: String,
    val unitsRequired: Int,
    val unitsFulfilled: Int
)

enum class ContractType {
    PROCUREMENT, TRANSPORT, SHUTTLE
}