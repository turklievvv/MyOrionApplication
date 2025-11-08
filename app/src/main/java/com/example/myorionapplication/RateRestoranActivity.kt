package com.example.myorionapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import com.example.myorionapplication.PayStories.PayStoryActivity
import com.google.android.material.textfield.TextInputEditText

class RateRestoranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rate_restoran)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextPolosa = findViewById<ImageView>(R.id.imageView2)
        val editText = findViewById<TextInputEditText>(R.id.rateEditText)
        val rateText = findViewById<TextView>(R.id.rateText)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val button = findViewById<MaterialButton>(R.id.gotovoButton)
        val backButton = findViewById<MaterialButton>(R.id.backButton)
        button.setOnClickListener {
            finish()
            Toast.makeText(this, "Спасибо за отзыв!!!", Toast.LENGTH_SHORT).show()
        }
        backButton.setOnClickListener { finish() }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            when (rating.toInt()) {
                5 -> {
                    rateText.text = "Отлично"
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.green))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.green)
                    )
                    editText.isVisible = false
                    editTextPolosa.isVisible = false

                }

                4 -> {
                    rateText.text = "Хорошо"
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.orange)
                    )
                    editText.isVisible = false
                    editTextPolosa.isVisible = false
                }

                3 -> {
                    rateText.text = "Неплохо"
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.orange)
                    )
                    editText.isVisible = true
                    editTextPolosa.isVisible = true
                }

                2 -> {
                    rateText.text = "Плохо"
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.red))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.red)
                    )
                    editText.isVisible = true
                    editTextPolosa.isVisible = true
                }

                1 -> {
                    rateText.text = "Ужасно"
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.red))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.red)
                    )
                    editText.isVisible = true
                    editTextPolosa.isVisible = true
                }

                else -> {
                    rateText.text = "Оцените нас"
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    rateText.isVisible = false
                    editTextPolosa.isVisible = false
                }
            }
        }
    }
}