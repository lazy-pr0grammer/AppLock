package com.aylax.applock.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Executor : Executor {

    private var executor: Executor = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable?) {
        executor.execute(command)
    }


}