package com.elka.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
  private val localBroadcastManager by lazy {
    LocalBroadcastManager.getInstance(this)
  }

  private lateinit var progressBar: ProgressBar
  private val receiver = object : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
      if (intent?.action == LoaderService.ACTION_LOAD) {
        val progress = intent.getIntExtra(LoaderService.LOAD_PROGRESS, 0)
        progressBar.progress = progress
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // init ui
    progressBar = findViewById(R.id.progress_bar)

    // reg receiver
    val intentFilter = IntentFilter().apply { addAction(LoaderService.ACTION_LOAD) }
    localBroadcastManager.registerReceiver(receiver, intentFilter)

    // start service
    Intent(this, LoaderService::class.java).apply {
      startService(this)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    localBroadcastManager.unregisterReceiver(receiver)
  }
}