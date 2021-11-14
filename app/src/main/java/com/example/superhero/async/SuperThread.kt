package com.example.superhero.async

import java.util.concurrent.BlockingQueue


class SuperThread(private val taskQueue: BlockingQueue<Runnable>) {

    private var thread: Thread

    @get:Synchronized
    var isStopped = false
        private set

    @get:Synchronized
    var isWorking = false
        private set

    init {
        thread = Thread {
            while (!isStopped) {
                try {
                    val task = taskQueue.take()
                    isWorking = true
                    task.run()
                    isWorking = false
                } catch (e: InterruptedException) {
                    // blah
                }
            }
        }
        thread.start()
    }

    @Synchronized
    fun doStop() {
        isStopped = true
        thread.interrupt()
    }


}