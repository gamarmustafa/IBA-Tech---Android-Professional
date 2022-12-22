package com.example.test_ibatech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : AppCompatActivity() {
    val state = MutableStateFlow("empty")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runSync()
        runAsync()
        // subscribe to flow updates and print state.value to logcat.
    }

    fun runSync() {
        println("runSync method.")
        for (i in 1..1000) {
            GlobalScope.launch(Dispatchers.Main) {
                doWork(i.toString())
                Log.i("sync", state.value)
            }
        }
        //launch 1000 coroutines. Invoke doWork(index/number of coroutine) one after another. Example 1, 2, 3, 4, 5, etc.
    }


    fun runAsync() {
        println("runAsync method.")
        for (i in 1..1000) {
            GlobalScope.launch {
                doWork(i.toString())
                Log.i("async", state.value)
            }
        }
        // launch 1000 coroutines. Invoke doWork(index/number of coroutine) in async way. Example 1, 2, 5, 3, 4, 8, etc.
    }

    private suspend fun doWork(name: String) {
        delay(500)
        state.update { "$name Completed" }
    }
}