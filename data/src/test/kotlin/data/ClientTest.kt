package data

import com.superhero.lib.Client
import org.junit.Test

class ClientTest {

    val client = Client()

    @Test
    fun login() {
        val respo = client.login("string", "string")

        assert(respo.accessToken.isNotEmpty())
    }

    @Test
    fun register() {

        val res = client.register("blah", "bluh", "you are gay")

        println(res.body?.string())
        assert(res.code == 200)
    }
}