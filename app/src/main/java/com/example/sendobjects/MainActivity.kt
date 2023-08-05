package com.example.sendobjects

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

private const val MY_ACTION = "com.sendObject.ACTION"
private const val MY_KEY = "com.sendObject.KEY"

class MainActivity : ComponentActivity() {

    @Suppress("DEPRECATION")
    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != null && intent.action == MY_ACTION) {
                val users = intent.getParcelableArrayListExtra<User>(MY_KEY)
                if (users != null) {
                    Toast.makeText(context, "" + users, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.btn_send)
        val buttonSendExplicit : Button = findViewById(R.id.btn_send_explicit)

        button.setOnClickListener {
            sendListObject()
        }

        buttonSendExplicit.setOnClickListener {
            sendListObjectExplicit()
        }
    }

    private fun sendListObject() {
        val intent = Intent(MY_ACTION)
        val users = ArrayList<User>()
        users.add(User(1, "user1"))
        users.add(User(2, "user2"))

        intent.putExtra(MY_KEY, users)
        sendBroadcast(intent)
    }

    private fun sendListObjectExplicit() {
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val users = ArrayList<User>()
        users.add(User(1, "user1"))
        users.add(User(2, "user2"))

        intent.putExtra(MY_KEY, users)
        sendBroadcast(intent)
    }

    override fun onStart() {
        super.onStart()
        val intent = IntentFilter(MY_ACTION)
        registerReceiver(mBroadcastReceiver, intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
    }
}