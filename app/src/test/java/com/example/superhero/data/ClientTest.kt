package com.example.superhero.data

import junit.framework.TestCase
import org.junit.Test

class ClientTest {

    val client = Client()

    @Test
    fun login() {
        val respo = client.login("blah", "bluh")
        println(respo.body?.string())

        assert(respo.body != null)
    }
}