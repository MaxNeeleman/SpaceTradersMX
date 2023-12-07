package com.github.maxneeleman.plugins

import com.github.maxneeleman.client
import com.github.maxneeleman.models.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.decode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/status") {
            val response : HttpResponse = client.get("https://api.spacetraders.io/v2/")
            val responseText: String = response.body<String>()
            call.respondText(responseText)
        }
        get("/agent") {
            val response : HttpResponse = client.get("https://api.spacetraders.io/v2/my/agent")
            val responseText: String = response.body<String>()
            val apiResponseAgent: ApiResponseAgent = Json.decodeFromString(responseText)
            call.respond(apiResponseAgent.agent)
        }
        get("/contracts") {
            val response : HttpResponse = client.get("https://api.spacetraders.io/v2/my/contracts")
            val responseText: String = response.body<String>()
            val apiResponseContract: ApiResponseContract = Json.decodeFromString(responseText)
            call.respond(apiResponseContract.contracts)
        }
        get("/contracts/{id}/accept") {
            val id = call.parameters["id"]
            val response : HttpResponse = client.post("https://api.spacetraders.io/v2/my/contracts/$id/accept")
            val responseText: String = response.body<String>()
            val apiResponseContract: ApiResponseContract = Json.decodeFromString(responseText)
            call.respond(apiResponseContract.contracts)
        }
        get("/systems") {
            val response : HttpResponse = client.get("https://api.spacetraders.io/v2/systems")
            val responseText: String = response.body<String>()
            val apiResponseSystem: ApiResponseSystem = Json.decodeFromString(responseText)
            call.respond(apiResponseSystem.systems)
        }
        get("/systems/{symbol}") {
            val symbol = call.parameters["symbol"]
            val response : HttpResponse = client.get("https://api.spacetraders.io/v2/systems/$symbol")
            val responseText: String = response.body<String>()
            val apiResponseSingleSystem: ApiResponseSingleSystem = Json.decodeFromString(responseText)
            call.respond(apiResponseSingleSystem.system)
        }
    }
}
