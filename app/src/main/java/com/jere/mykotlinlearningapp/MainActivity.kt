package com.jere.mykotlinlearningapp

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val languageName: Tag = "jereTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("", "")

        clickBtn.setOnClickListener(View.OnClickListener {
            startSecondActivity()
        })


    }


    private fun startSecondActivity() {
        val helloTvString = helloTv.text.toString()
        Log.d("test= ", helloTvString)
        helloTv.setText(R.string.app_name)
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }



}
