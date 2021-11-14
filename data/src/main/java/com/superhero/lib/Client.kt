package com.superhero.lib

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.net.InetSocketAddress
import java.net.Proxy

class Client {

    private val client = OkHttpClient()
        .newBuilder()
        .proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(8888)))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private val baseUrl = HttpUrl.Builder()
        .scheme("http")
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

    fun register(userName: String, password: String, name: String): Response {

        @Serializable
        data class Registration(val userName: String, val password: String, val name: String)

        val url = baseUrl.newBuilder("/auth/register")!!
            .build()

        val postData = serializer.encodeToString(
            Registration(userName, password, name)
        )

        println(postData)
        val request = Request.Builder()
            .post(postData.toRequestBody())
            .addHeader("Content-Type", "application/json")
            .url(url)
            .build()

        return client.newCall(request).execute()
    }

}

@Serializable
data class LoginResponse(
    val userId: Int,
    val userName: String,
    val name: String,
    val accessToken: String
)