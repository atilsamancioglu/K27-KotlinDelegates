package com.atilsamancioglu.kotlindelegateloggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(), LifecycleLogger by LifeCycleLoggerImplementation() {

    //property delegates
    private val myVariable by lazy {
        println("hello this is a lazy implementation")
        //initializing this object when first time we reach this object, not before
        10
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerLifecycleOwner(this)

        println(myVariable)
    }
}


interface LifecycleLogger {
    fun registerLifecycleOwner(owner: LifecycleOwner)
}

class LifeCycleLoggerImplementation : LifecycleLogger, LifecycleEventObserver {
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit
        }
    }
}