package com.example.myorionapplication.PayStories

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myorionapplication.Notifications.NotificationAdapter
import com.example.myorionapplication.R
import com.example.myorionapplication.module.Notification
import com.example.myorionapplication.module.PayStory
import com.google.android.material.button.MaterialButton

class PayStoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pay_story)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val payStoryList = mutableListOf<PayStory>(
            PayStory(
                title = "Бакари 800р",
                data = "10 апреля",
                bonus = "+120",
                minusBonus = "-710",
                photo = R.drawable.bakari,
                newPay = true
            ), PayStory(
                title = "Docle Salato 4000р",
                data = "21 апреля",
                bonus = "+310",
                minusBonus = "-0",
                photo = R.drawable.dolce_salato,
                newPay = true
            ), PayStory(
                title = "Пиросмани 1200р",
                data = "10 апреля",
                bonus = "0",
                minusBonus = "-110",
                photo = R.drawable.pirosmani,
                newPay = true
            ), PayStory(
                title = "Бакари 800р",
                data = "10 апреля",
                bonus = "+120",
                minusBonus = "-710",
                photo = R.drawable.bakari,
                newPay = true
            ), PayStory(
                title = "Docle Salato 4000р",
                data = "21 апреля",
                bonus = "+310",
                minusBonus = "-0",
                photo = R.drawable.dolce_salato,
                newPay = true
            ), PayStory(
                title = "Пиросмани 1200р",
                data = "10 апреля",
                bonus = "0",
                minusBonus = "-110",
                photo = R.drawable.pirosmani,
                newPay = true
            )

        )

        val backButton = findViewById<MaterialButton>(R.id.backButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPayStory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PayStoryAdapter(payStoryList, this)
        backButton.setOnClickListener {
            finish()
        }
    }
}