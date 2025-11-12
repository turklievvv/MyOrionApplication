package com.example.myorionapplication.Notifications

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

lateinit var notificationBanner: LinearLayout
lateinit var enableNotification: TextView

class NotificationsActvity : AppCompatActivity() {

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


        if (areNotificationsEnabled()) {
            notificationBanner.visibility = View.GONE
        } else {
            notificationBanner.visibility = View.VISIBLE
        }

        // Клик на "Включить уведомления"
        enableNotification.setOnClickListener {
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }
            startActivity(intent)

        }

        val notificationList = mutableListOf<Notification>(
            Notification(
                titleText = "Дарим 3000 бонусов!!!",
                text = "Дарим 3000 бонусов за ваш первый заказ через доставку",
                photo = R.drawable.orion_svj,
                data = "Сегодня",
                newNotification = true
            ),
            Notification(
                titleText = "Отрытие нового ресторана",
                text = "20 апреля приглашаем всех на открытие нового ресторана в Черкесске",
                photo = R.drawable.info_black,
                data = "20 октября",
                newNotification = true
            ),
            Notification(
                titleText = "Пицца в подарок",
                text = "12,13,14 котября при заказе в Усть-Джегуту на сумму от 600Р пицца бесплатна",
                photo = R.drawable.orion_svj,
                data = "10 октября",
                newNotification = true
            ),
            Notification(
                titleText = "MMC пиред",
                text = "Присоеденяйтесь к нам в MMC",
                photo = R.drawable.android_black,
                data = "5 июня",
                newNotification = true
            ),
            Notification(
                titleText = "Роллы по 250Р",
                text = "Весь октябрь в Dolce Salato роллы по 250Р",
                photo = R.drawable.orion_svj,
                data = "2 октября",
                newNotification = true
            ),
            Notification(
                titleText = "Дарим 200 баллов",
                text = "Дарим 200 баллов на счет при заказе доставки через приложение",
                photo = R.drawable.orion_svj,
                data = "11 сентября",
                newNotification = true
            ),
            Notification(
                titleText = "Дарим 3000 бонусов!",
                text = "Дарим 3000 бонусов за ваш первый заказ через доставку",
                photo = R.drawable.orion_svj,
                data = "Сегодня",
                newNotification = true
            ),

            )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewNotification)
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
                Toast.makeText(this, "Уведомления включены", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Уведомления отключены", Toast.LENGTH_SHORT).show()
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
