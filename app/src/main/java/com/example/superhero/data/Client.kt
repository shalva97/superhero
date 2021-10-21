package com.example.superhero.data

import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaDuration

class Client {

    private val client = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    private val baseUrl = HttpUrl.Builder()
        .scheme("https")
        .host("commschool-android-api.herokuapp.com")
        .build()

    fun login(userName: String, password: String): Response {

        val url = baseUrl.newBuilder("auth/login")!!
            .addQueryParameter("username", userName)
            .addQueryParameter("password", password)
            .build()

        val request = Request.Builder()
            .post("".toRequestBody())
            .url(url)
            .build()

        return client.newCall(request).execute()
    }

}


