package com.example.superhero.data

import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody

class Client {
    private val client = OkHttpClient()

    private val baseUrl = HttpUrl.Builder()
        .scheme("https")
        .host("commschool-android-api.herokuapp.com")
        .build()

    fun login(userName: String, password: String): Response {

        val url = baseUrl.newBuilder("auth/login")!!
            .addQueryParameter("username", "blah")
            .addQueryParameter("password", "blah")
            .build()

        val request = Request.Builder()
            .post("".toRequestBody())
            .url(url)
            .build()

        return client.newCall(request).execute()
    }

}


