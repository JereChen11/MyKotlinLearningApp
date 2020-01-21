package com.jere.mykotlinlearningapp.multiplethread.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jere.mykotlinlearningapp.R
import kotlinx.android.synthetic.main.activity_test_async_task.*
import java.lang.ref.WeakReference

class TestAsyncTaskActivity : AppCompatActivity(), View.OnClickListener {
    private var mAsyncTask: MyAsyncTask? = null

    companion object {
        const val TAG = "JereTest"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_async_task)

        mAsyncTask = MyAsyncTask(this)

        startBtn.setOnClickListener(this)
        cancelBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.startBtn -> {
                mAsyncTask?.execute("jereTest")
                startBtn.isEnabled = false
            }
            R.id.cancelBtn -> mAsyncTask?.cancel(true)
        }
    }

    class MyAsyncTask(activity: TestAsyncTaskActivity) : AsyncTask<String, Int, String>() {
        private var weakRef: WeakReference<TestAsyncTaskActivity>? = null

        init {
            weakRef = WeakReference(activity)
        }

        override fun onPreExecute() {
            super.onPreExecute()
            val activity = weakRef?.get()
            if (activity != null && !activity.isFinishing) {
                Toast.makeText(activity, "MyAsyncTask onPreExecute()", Toast.LENGTH_SHORT).show()
            }
        }

        override fun doInBackground(vararg params: String?): String {
            for (i in 0..10) {
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                //通过调用publishProgress()方法，将执行进度传递给onProgressUpdate()
                publishProgress(i)
                if (isCancelled) {
                    break
                }
            }
            return params[0]!!
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val activity = weakRef?.get()
            if (activity != null && !activity.isFinishing) {
                activity.progressBar.progress = values[0]!!
                activity.displayTv.text = "onProgressUpdate: value = " + values[0]
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val activity = weakRef?.get()
            if (activity != null && !activity.isFinishing) {
                Toast.makeText(activity, "MyAsyncTask onPostExecute()" + result, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        override fun onCancelled() {
            super.onCancelled()
            Log.e(TAG, "click cancel button, cancel task execute")
            val activity = weakRef?.get()
            if (activity != null && !activity.isFinishing) {
                Toast.makeText(activity, "cancel task", Toast.LENGTH_SHORT).show()
            }
        }


    }

}
