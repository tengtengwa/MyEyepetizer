package com.example.main.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.base.BaseActivity
import com.example.main.R

class CacheActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_cache)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, this::class.java))
        }
    }
}