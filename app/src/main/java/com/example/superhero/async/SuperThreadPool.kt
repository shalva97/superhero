package com.example.superhero.async

import java.util.concurrent.ArrayBlockingQueue
import java.util.ArrayList


class SuperThreadPool(numberOfThreads: Int, queue: Int) {

    private var taskQueue = ArrayBlockingQueue<Runnable>(queue)
    private val superThreads = ArrayList<SuperThread>()
    private var isStopped = false

    init {
        for (i in 0 until numberOfThreads) {
            superThreads.add(SuperThread(taskQueue))
        }
    }

    @Synchronized
    fun stop() {
        isStopped = true
        for (runnable in superThreads) {
            runnable.doStop()
        }
    }

    @Synchronized
    @Throws(Exception::class)
    fun execute(task: Runnable) {
        check(!isStopped) {
            "Thread Pool stopped!"
        }
        taskQueue.offer(task)
    }

    @Synchronized
    fun awaitAll() {
        while (taskQueue.size > 0 || superThreads.any { it.isWorking }) {
            try {
                Thread.sleep(1)
            } catch (e: InterruptedException) {
                // blah
            }
        }
    }
}