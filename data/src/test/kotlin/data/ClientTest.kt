package data

import com.superhero.lib.Client
import org.junit.Test

class ClientTest {

    val client = Client()

    @Test
    fun login() {
        val respo = client.login("blah", "bluh")

        println(respo.accessToken)

        assert(respo != null)
    }

    @Test
    fun jsonBuilder() {

        val res = client.register("blah", "bluh", "you are gay")

        println(res)
    }
}