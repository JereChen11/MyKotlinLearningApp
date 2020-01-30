package com.jere.mykotlinlearningapp.multiplethread.intentservice

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyIntentService : IntentService("MyIntentService") {
    companion object {
        private val TAG: String = "MyIntentService"
        public val DOWNLOAD_ACTION = "DOWNLOAD_ACTION"
        public val READ_ACTION = "READ_ACTION"
        public val TEST_AUTHOR = "TEST_AUTHOR"
    }


    override fun onHandleIntent(intent: Intent?) {
        val action: String? = intent?.action
        val author: String? = intent?.extras?.getString(TEST_AUTHOR)

        when (intent?.action) {
            DOWNLOAD_ACTION -> {
                for (i in 0..10) {
                    Thread.sleep(1000)
                    Log.e(TAG, "$author $action $i")
                }
            }
            READ_ACTION -> {
                for (j in 0..10) {
                    Thread.sleep(2000)
                    Log.e(TAG, "$author $action $j")
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
        Toast.makeText(this, "conCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }
}
