package com.example.superhero

import junit.framework.Assert.assertEquals
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun okhttp() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .get()
            .url("http://google.com")
            .build()
        val res = client.newCall(request).execute()
         client.newCall(request).execute()
        client.newCall(request).execute()

        assert(res != null)
    }

    @Test
    fun httpurl() {
        val baseUrl = HttpUrl.Builder()
            .scheme("https")
            .host("commschool-android-api.herokuapp.com")
            .build()

        println(baseUrl.newBuilder("auth/login"))
    }
}