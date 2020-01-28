package com.jere.mykotlinlearningapp.multiplethread.handlerThread

import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.R
import kotlinx.android.synthetic.main.activity_test_handler_thread.*

class TestHandlerThreadActivity : AppCompatActivity(), View.OnClickListener {
    var mMainHandler: Handler? = null
    var mWorkHandler: WorkHandler? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.handlerThreadStart1Btn -> mWorkHandler?.obtainMessage(1)?.sendToTarget()
            R.id.handlerThreadStart2Btn -> mWorkHandler?.obtainMessage(2)?.sendToTarget()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_handler_thread)

        mMainHandler = Handler(Looper.getMainLooper())

        val mHandlerThread = HandlerThread("JereTest")
        mHandlerThread.start()
        mWorkHandler = WorkHandler(mHandlerThread.looper)

        handlerThreadStart1Btn.setOnClickListener(this)
        handlerThreadStart2Btn.setOnClickListener(this)
    }

    inner class WorkHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    for (i in 1..5) {
                        Thread.sleep(1000)
                        mMainHandler?.post { handlerThreadProgressBar1.progress = i }
                    }
                }
                2 -> {
                    for (j in 1..5) {
                        Thread.sleep(1000)
                        mMainHandler?.post { handlerThreadProgressBar2.progress = j }
                    }
                }
            }
        }

    }

}
