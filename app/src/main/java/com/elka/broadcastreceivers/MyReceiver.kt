package com.elka.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context?, intent: Intent?) {
    when (intent?.action) {
      ACTION_CLICKED -> {
        val countOfClicks = intent.getIntExtra(COUNT_OF_CLICKS, 0)
        Toast.makeText(context, "Clicked $countOfClicks times", Toast.LENGTH_SHORT).show()
      }
      Intent.ACTION_BATTERY_LOW -> Toast.makeText(context, "Battery low", Toast.LENGTH_SHORT).show()
      Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
        val turnedOn = intent.getBooleanExtra("state", false)
        Toast.makeText(context, "Airplane mode changed. Turned on: $turnedOn", Toast.LENGTH_SHORT).show()
      }
    }
  }

  companion object {
    const val COUNT_OF_CLICKS = "COUNT_OF_CLICKS"
    const val ACTION_CLICKED = "action_clicker"
  }
}