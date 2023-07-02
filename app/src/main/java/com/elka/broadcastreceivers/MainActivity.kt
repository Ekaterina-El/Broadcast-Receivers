package com.elka.broadcastreceivers

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private var countOfClicks = 0
  private val receiver = MyReceiver()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.btn).setOnClickListener {
      countOfClicks++
      val broadcastIntent = Intent(MyReceiver.ACTION_CLICKED)
        .putExtra(MyReceiver.COUNT_OF_CLICKS, countOfClicks)
      sendBroadcast(broadcastIntent)
    }

    val intentFilter = IntentFilter().apply {
      addAction(Intent.ACTION_BATTERY_LOW)
      addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
      addAction(MyReceiver.ACTION_CLICKED)
    }
    registerReceiver(receiver, intentFilter)
  }

  override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(receiver)
  }
}