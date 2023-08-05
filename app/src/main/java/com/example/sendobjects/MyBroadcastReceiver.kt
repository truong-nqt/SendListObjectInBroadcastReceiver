package com.example.sendobjects

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

private const val MY_KEY = "com.sendObject.KEY"

class MyBroadcastReceiver : BroadcastReceiver() {

    @Suppress("DEPRECATION")
    override fun onReceive(context: Context?, intent: Intent?) {
        val users = intent?.getParcelableArrayListExtra<User>(MY_KEY)
        if (users != null) {
            Toast.makeText(context, "Explicit " + users, Toast.LENGTH_LONG).show()
        }
    }
}