package com.example.extracredit_hw2_rewritten_in_kotlin

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedState: Bundle?) {

        super.onCreate(savedState)
        setContentView(R.layout.activity_second)

        val m = intent.getStringExtra(MainActivity.MESSAGE_ID) //get intent value from first

        // if value is null or empty display "The...age"
        val message = if (!m.isNullOrEmpty()) m
        else "There is no message"

        intent_message.text = message//put message on TextView
        //print statement can be used to directly print to console
        println("$TAG, $message")
    }

    override fun onStart() {
        super.onStart()
        println("$CON, started")
        Log.i(CON, this.lifecycle.currentState.toString())
    }

    override fun onResume() {
        super.onResume()
        println("$CON, I am back!")
        Log.i(CON, this.lifecycle.currentState.toString())
    }

    override fun onPause() {
        super.onPause()
        println("$CON, Let's take a break.")
    }

    override fun onStop() {
        super.onStop()
        println("$CON, Halt your horse!")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("$CON, I will be back (Thumb up)")
    }

    companion object {
        const val TAG = "Message from 1st Act: "
        const val CON = "Message from 2nd Act: "
    }
}
