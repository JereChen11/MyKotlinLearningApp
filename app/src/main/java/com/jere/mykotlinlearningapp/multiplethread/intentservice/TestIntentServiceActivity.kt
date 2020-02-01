package com.jere.mykotlinlearningapp.multiplethread.intentservice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.Messenger
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.R
import kotlinx.android.synthetic.main.activity_test_intent_service.*
import java.lang.ref.WeakReference

class TestIntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent_service)

        val intent = Intent()
        intent.setClass(this, MyIntentService::class.java)
        intent.action = MyIntentService.DOWNLOAD_ACTION_KEY
        intent.putExtra(MyIntentService.TEST_AUTHOR_KEY, "Jere")
        //将 Messenger 传递给 IntentService, 使其传递消息回来，实现客户端与服务端之间进行沟通
        intent.putExtra(MyIntentService.TEST_MESSENGER_KEY, Messenger(MessengerHandler(this)))
        startService(intent)

//        val jamesIntent = Intent()
//        jamesIntent.setClass(this, MyIntentService::class.java)
//        jamesIntent.action = MyIntentService.READ_ACTION_KEY
//        jamesIntent.putExtra(MyIntentService.TEST_AUTHOR_KEY, "James")
//        startService(jamesIntent)
    }

    /**
     * 此 Handler 用于创建 Messenger 对象，接收 IntentService 回复回来的消息
     *
     * 静态内部类，防止内存泄漏
     */
    class MessengerHandler(activity: TestIntentServiceActivity) : Handler() {
        var weakReference: WeakReference<TestIntentServiceActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            //msg 为 IntentService 回复的消息，包含 Bundle 等信息。
            val bundle: Bundle = msg.data
            //获取 IntentService 传递过来的 下载进度 参数
            val downloadProgressBarValue: Int =
                bundle.get(MyIntentService.DOWNLOAD_PROGRESS_BAR_VALUE_KEY) as Int
            val activity: TestIntentServiceActivity? = weakReference.get()
            //将下载进度设置成 ProgressBar 的进度，显示出来。
            if (activity != null && !activity.isFinishing) {
                activity.intentServiceDownloadProgressBar.progress = downloadProgressBarValue
            }
        }
    }
}
