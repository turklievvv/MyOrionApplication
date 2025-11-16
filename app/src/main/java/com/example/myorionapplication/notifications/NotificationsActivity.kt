package com.example.myorionapplication.notifications

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myorionapplication.R
import com.example.myorionapplication.module.Notification
import com.example.myorionapplication.module.NotificationRepository


class NotificationsActvity : AppCompatActivity() {

    lateinit var notificationBanner: LinearLayout
    lateinit var enableNotification: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var notificationList : List<Notification>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notifications_actvity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notificationBanner = findViewById<LinearLayout>(R.id.notificationsBanner)
        enableNotification = findViewById<TextView>(R.id.enableNotification)
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
        notificationList = NotificationRepository.getNotifications()

        if (areNotificationsEnabled()) {
            notificationBanner.visibility = View.GONE
        } else {
            notificationBanner.visibility = View.VISIBLE
        }

        enableNotification.setOnClickListener {
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }
            startActivity(intent)
        }



        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewNotification)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NotificationAdapter(notificationList, this)

        checkAndRequestNotificationPermission()
    }

    private fun areNotificationsEnabled(): Boolean {
        val manager = NotificationManagerCompat.from(this)
        return manager.areNotificationsEnabled()
    }

    override fun onResume() {
        super.onResume()
        if (areNotificationsEnabled()) {
            notificationBanner.isVisible = false
        } else {
            notificationBanner.isVisible = true
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, getString(R.string.notificationEnabled), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.notificationNotEnabled), Toast.LENGTH_SHORT).show()
            }
        }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // API 33+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

}
