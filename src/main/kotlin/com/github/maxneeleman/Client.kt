package com.github.maxneeleman

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

val token = System.getenv("BEARER_TOKEN_MX")
val client = HttpClient(CIO) {
    defaultRequest {
        header("Authorization", "Bearer $token")
    }
    expectSuccess = false
    engine {
        maxConnectionsCount = 1000
        endpoint {
            maxConnectionsPerRoute = 1000
            pipelineMaxSize = 20
            keepAliveTime = 5000
            connectTimeout = 5000
            connectAttempts = 5
        }
    }
}