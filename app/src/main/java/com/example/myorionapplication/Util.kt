package com.example.myorionapplication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

inline fun CoroutineScope.startCountDownTimer(
    startTime: Long,
    period: Long = 1000L,
    crossinline start: suspend (Long) -> Unit = {},
    crossinline end: suspend (Long) -> Unit = {},
    crossinline tick: suspend (Long) -> Unit = {},
): Job {
    require(startTime > 0L) { "Start time must be positive" }
    require(period > 0L) { "Delay must be positive" }

    return launch {
        var time = startTime
        launch { start.invoke(time) }

        while (time > 0L) {
            try {
                delay(period)
            } catch (_: CancellationException) {
                break
            }
            time = maxOf(0L, time - period)
            launch { tick.invoke(time) }
        }

        withContext(NonCancellable) { end.invoke(time) }
    }
}
