package com.github.maxneeleman

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZGVudGlmaWVyIjoiTUFYTiIsInZlcnNpb24iOiJ2Mi4xLjQiLCJyZXNldF9kYXRlIjoiMjAyMy0xMi0wMiIsImlhdCI6MTcwMTg5MDEwNiwic3ViIjoiYWdlbnQtdG9rZW4ifQ.Ppg35HIb_WOTlvR1adFfl6ONCtB3OYwnc4E-OJ_D8nOY-LKC2w-kSQ1uNO2n4r8A1NwA8U8SAuufTRTwSJAz4XK_PEhyEnAOOvDkIdxTKsOI79v_oyaSPZezg1u_zsLkjS6gGme0Yrr8t81RE-hIFKvNyf5D9bQBNpsZ-JNrv34g0j1VN7SzOIyqwEmgrjHwCTp22crBj_NNst3LVyspQzStJ9xc3Q2sAqEdKdwXP56QUgbL0vTM0CXq2mFoFFTS83o2YlqT0gA5h8wTW6nRt1O8Zv1YS8IkmL7mxGZB4HJNxqS7M6qbDjLkdsuDM8o8WfiBa54Nvqa5jF8e1Yu8Hg"

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