package com.example.superhero

import com.example.superhero.async.SuperThreadPool
import org.junit.Test

class ThreadPoolTest {

    private val pool = SuperThreadPool(numberOfThreads = 4, queue = 8)

    @Test
    fun runFunctionThatPrintsStuff() {
        val results = mutableListOf<String>()

        pool.launch {
            Thread.sleep(100)
            results.add("blah")
        }

        pool.launch {
            Thread.sleep(40)
            results.add("one")
        }

        pool.launch {
            Thread.sleep(150)
            results.add("two")
        }

        assert(results.size != 3)
        pool.awaitAll()
        assert(results.size == 3)
        println(results)
    }
}