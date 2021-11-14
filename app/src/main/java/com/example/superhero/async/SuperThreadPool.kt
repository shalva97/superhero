package com.example.superhero.async

import java.util.concurrent.ArrayBlockingQueue

class SuperThreadPool(numberOfThreads: Int, queue: Int) {

    private var taskQueue = ArrayBlockingQueue<Pair<(Throwable) -> Unit ,Runnable>>(queue)
    private val superThreads = List<SuperThread>(numberOfThreads) { SuperThread(taskQueue) }
    private var isStopped = false

    @Synchronized
    fun stop() {
        isStopped = true
        for (runnable in superThreads) {
            runnable.doStop()
        }
    }

    @Synchronized
    fun launch(handler: (Throwable) -> Unit, task: Runnable) {
        taskQueue.offer(handler to task)
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