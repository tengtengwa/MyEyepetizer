package com.example.main.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.base.BaseActivity
import com.example.main.R

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_settings)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, this::class.java))
        }
    }
}