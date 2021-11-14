package data

import com.superhero.lib.Client
import junit.framework.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

class ClientTest {

    val client = Client()

    @Test
    fun login() {
        val respo = client.login("string", "string")

        assertTrue(respo.accessToken.isNotEmpty())
    }

    @Ignore
    @Test
    fun register() {

        val res = client.register("blah", "bluh", "gay")

        println(res.body?.string())
        assertTrue(res.code == 200)
    }
}