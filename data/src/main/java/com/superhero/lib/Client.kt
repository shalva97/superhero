package com.superhero.lib

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class Client {
    private val client = OkHttpClient()

    private val baseUrl = HttpUrl.Builder()
        .scheme("https")
        .host("commschool-android-api.herokuapp.com")
        .build()

    private val serializer = Json {
        ignoreUnknownKeys = true
    }

    fun login(userName: String, password: String): LoginResponse {

        val url = baseUrl.newBuilder("auth/login")!!
            .addQueryParameter("username", userName)
            .addQueryParameter("password", password)
            .build()

        val request = Request.Builder()
            .post("".toRequestBody())
            .url(url)
            .build()

        val response = client.newCall(request).execute().body?.string()!!
        return serializer.decodeFromString<LoginResponse>(response)
    }

    fun register(userName: String, password: String, name: String) {

        @Serializable
        data class Registration(val userName: String, val password: String, val name: String)

        val url = baseUrl.newBuilder("auth/login")!!
            .build()

        val postData = serializer.encodeToString(
            Registration(userName, password, name)
        )

        val request = Request.Builder()
            .post(postData.toRequestBody())
            .url(url)
            .build()

        client.newCall(request)
    }

}

@Serializable
data class LoginResponse(
    val userId: Int,
    val userName: String,
    val name: String,
    val accessToken: String
)