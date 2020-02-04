package com.jere.mykotlinlearningapp.multiplethread.threadaddhandler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.R
import kotlinx.android.synthetic.main.activity_thread_add_handler.*
import java.lang.ref.WeakReference

//todo need register this activity on Manifest.xml
class TestHandlerPostRunnableActivity : AppCompatActivity() {
    private var mMainHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_add_handler)

        handlerAddThreadStartBtn.setOnClickListener(View.OnClickListener {
            //工作线程开始模拟下载任务
            val workThread: WorkThread = WorkThread(this)
            workThread.start()
        })

        //创建 Handler，关联App的 主Looper 对象
        mMainHandler = Handler(Looper.getMainLooper())
    }

    class WorkThread(private var activity: TestHandlerPostRunnableActivity) : Thread() {
        private var handler: Handler? = activity.mMainHandler

        override fun run() {
            super.run()
            for (i in 0..6) {
                sleep(1000)
                //新建 Runnable 设置进度参数传，然后通过 post(Runnable) 方法，让其更新 progressBar 进度
                val runnable: MyRunnable = MyRunnable(activity, i)
                handler?.post(runnable)
            }
        }
    }


    /**
     * 处理 UI 工作。
     * 静态内部类，防止内存泄露
     */
    class MyRunnable(activity: TestHandlerPostRunnableActivity, value: Int) : Runnable {
        private var weakReference = WeakReference(activity)
        private var progressValue = value

        override fun run() {
            val activity = weakReference.get()
            if (activity != null && !activity.isFinishing) {
                //获取任务执行进度参数，更新 progressBar 进度
                activity.handlerAddThreadProgressBar.progress = progressValue
            }
        }
    }
}