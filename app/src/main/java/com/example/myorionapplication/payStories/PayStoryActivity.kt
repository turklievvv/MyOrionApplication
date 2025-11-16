package com.example.myorionapplication.payStories

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myorionapplication.R
import com.example.myorionapplication.module.PayStory
import com.google.android.material.button.MaterialButton

lateinit var payStoryList: List<PayStory>
lateinit var recyclerView: RecyclerView

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
        payStoryList = PayStoryRepository.getRepository()

        findViewById<MaterialButton>(R.id.backButton).setOnClickListener { finish() }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPayStory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PayStoryAdapter(payStoryList, this)
    }
}