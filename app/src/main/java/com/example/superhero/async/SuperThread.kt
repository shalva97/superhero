package com.example.superhero.async

import java.util.concurrent.ArrayBlockingQueue


class SuperThread(
    private val taskQueue: ArrayBlockingQueue<Pair<(Throwable) -> Unit, Runnable>>
) {

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
                val task = taskQueue.take()
                try {
                    isWorking = true
                    task.second.run()
                    isWorking = false
                } catch (e: InterruptedException) {
                    // blah
                } catch (throwable: Throwable) {
                    task.first.invoke(throwable)
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