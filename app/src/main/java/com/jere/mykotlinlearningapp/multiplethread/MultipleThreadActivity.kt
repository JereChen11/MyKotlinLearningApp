package com.jere.mykotlinlearningapp.multiplethread

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jere.mykotlinlearningapp.R
import com.jere.mykotlinlearningapp.multiplethread.asynctask.TestAsyncTaskActivity
import kotlinx.android.synthetic.main.activity_multiple_thread.*

class MultipleThreadActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_thread)
        asyncTaskTutorialBtn.setOnClickListener(this)
        handlerThreadTutorialBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id) {
            R.id.asyncTaskTutorialBtn ->
                startActivity(Intent(this, TestAsyncTaskActivity::class.java))
            R.id.handlerThreadTutorialBtn ->
                Toast.makeText(this, "Handler Thread", Toast.LENGTH_SHORT).show()
        }
    }

}
