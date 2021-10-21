package com.example.superhero.data

import junit.framework.TestCase
import org.junit.Test

class ClientTest : TestCase("random learning test") {

    val client = Client()

    @Test
    fun login() {
        val respo = client.login("blah", "bluh")
        println(respo.body)

        assert(respo.body != null)
    }
}