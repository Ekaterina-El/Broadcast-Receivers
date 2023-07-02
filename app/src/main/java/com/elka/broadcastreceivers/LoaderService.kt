package com.elka.broadcastreceivers

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

class LoaderService: Service() {
  private val localBroadcastManager by lazy {
    LocalBroadcastManager.getInstance(this)
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    thread {
      for (i in 1..10) {
        Thread.sleep(1000)
        Intent(ACTION_LOAD).apply {
          putExtra(LOAD_PROGRESS, i * 10)
          localBroadcastManager.sendBroadcast(this)
        }
      }
    }

    return super.onStartCommand(intent, flags, startId)
  }

  override fun onBind(p0: Intent?): IBinder? {
    return null
  }

   companion object {
     const val ACTION_LOAD = "ACTION_LOAD"
     const val LOAD_PROGRESS = "LOAD_PROGRESS"
   }
}