package com.jere.mykotlinlearningapp.multiplethread.intentservice

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

class MyIntentService : IntentService("MyIntentService") {
    companion object {
        private val TAG: String = "MyIntentService"
        val DOWNLOAD_ACTION_KEY = "DOWNLOAD_ACTION"
        val READ_ACTION_KEY = "READ_ACTION"
        val TEST_AUTHOR_KEY = "TEST_AUTHOR"
        val TEST_MESSENGER_KEY = "TEST_MESSENGER"
        val DOWNLOAD_PROGRESS_BAR_VALUE_KEY = "DOWNLOAD_PROGRESS_BAR_VALUE"
    }


    override fun onHandleIntent(intent: Intent?) {
        val action: String? = intent?.action
        val author: String? = intent?.extras?.getString(TEST_AUTHOR_KEY)

        when (intent?.action) {
            DOWNLOAD_ACTION_KEY -> {
                for (i in 0..6) {
                    Thread.sleep(1000)
                    Log.e(TAG, "$author $action $i")
                    //获取从 Activity 中传入的 Messenger 对象
                    val messenger: Messenger = intent.extras?.get(TEST_MESSENGER_KEY) as Messenger
                    //新建一个 Message 对象
                    val msg: Message = Message()
                    //为 Message 对象设置 下载进度 参数
                    val bundle: Bundle = Bundle()
                    bundle.putInt(DOWNLOAD_PROGRESS_BAR_VALUE_KEY, i)
                    msg.data = bundle
                    //Messenger 回复消息给 Activity
                    messenger.send(msg)
                }
            }
            READ_ACTION_KEY -> {
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
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.e(TAG, "onStart")
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }
}
