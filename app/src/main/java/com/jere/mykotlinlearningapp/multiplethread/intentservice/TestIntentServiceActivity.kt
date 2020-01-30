package com.jere.mykotlinlearningapp.multiplethread.intentservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.R

class TestIntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent_service)

        val intent = Intent()
        intent.setClass(this, MyIntentService::class.java)
        intent.action = MyIntentService.DOWNLOAD_ACTION
        intent.putExtra(MyIntentService.TEST_AUTHOR, "Jere")
        startService(intent)

        val jamesIntent = Intent()
        jamesIntent.setClass(this, MyIntentService::class.java)
        jamesIntent.action = MyIntentService.READ_ACTION
        jamesIntent.putExtra(MyIntentService.TEST_AUTHOR, "James")
        startService(jamesIntent)
    }
}
