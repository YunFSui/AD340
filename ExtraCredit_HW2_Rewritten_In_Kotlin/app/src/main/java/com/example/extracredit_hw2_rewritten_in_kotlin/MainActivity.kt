package com.example.extracredit_hw2_rewritten_in_kotlin

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.* //import layout

class MainActivity : AppCompatActivity() { //less wordy way to extend MainAct from AppCompat

    override fun onCreate(savedInstanceState: Bundle?) {//? for Nullable
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //assign onClickListener to my_button element in layout
        my_button.setOnClickListener{
            val message = message_box.text.toString() //val is immutable
            //with layout imported, no need to use findViewByID like in Java
            println(message)

            //per https://developer.android.com/guide/components/activities/intro-activities.html#kotlin
            //Kinda stupid for the class has to be class.java...
            val intentToSecond = Intent(this, SecondActivity::class.java)
                    .apply{ putExtra(MESSAGE_ID, message) }
            startActivity(intentToSecond)
        }
    }

    // ~ this is public static final in java, public = companion object{} const = static val = final
    companion object {

        const val MESSAGE_ID = "my.message"

    }

}