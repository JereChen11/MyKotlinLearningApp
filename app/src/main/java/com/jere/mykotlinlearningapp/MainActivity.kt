package com.jere.mykotlinlearningapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.multiplethread.MultipleThreadActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        multipleThreadTutorialBtn.setOnClickListener(View.OnClickListener {
//            startSecondActivity()
//        })

        multipleThreadTutorialBtn.setOnClickListener { startSecondActivity() }


    }


    private fun startSecondActivity() {
        val intent = Intent(this, MultipleThreadActivity::class.java)
        startActivity(intent)
    }


}
