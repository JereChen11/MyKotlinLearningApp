package com.jere.mykotlinlearningapp.multiplethread.threadaddhandler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.R
import kotlinx.android.synthetic.main.activity_thread_add_handler.*
import java.lang.ref.WeakReference

class TestThreadAddHandlerActivity : AppCompatActivity() {
    companion object {
        const val PROGRESS_VALUE_KEY = "PROGRESS_VALUE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_add_handler)

        handlerAddThreadStartBtn.setOnClickListener(View.OnClickListener {
            //工作线程开始模拟下载任务
            val workThread: WorkThread = WorkThread(this)
            workThread.start()
        })
    }

    class WorkThread(activity: TestThreadAddHandlerActivity) : Thread() {
        private var handler: MyHandler = MyHandler(activity)

        override fun run() {
            super.run()
            for (i in 0..6) {
                sleep(1000)
                //通过 Handler 将进度参数传递给 主线程，让其更新 progressBar 进度
                val message = Message()
                message.what = 1
                val bundle = Bundle()
                bundle.putInt(PROGRESS_VALUE_KEY, i)
                message.data = bundle
                handler.sendMessage(message)
            }
        }
    }

    /**
     * 静态内部类，防止内存泄漏
     */
    class MyHandler(activity: TestThreadAddHandlerActivity) : Handler() {
        var weakReference = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    val activity = weakReference.get()
                    if (activity != null && !activity.isFinishing) {
                        val progressValue: Int = msg.data.get(PROGRESS_VALUE_KEY) as Int
                        activity.handlerAddThreadProgressBar.progress = progressValue
                    }
                }
            }
        }
    }
}
