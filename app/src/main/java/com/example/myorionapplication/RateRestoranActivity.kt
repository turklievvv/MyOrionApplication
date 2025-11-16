package com.example.myorionapplication

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
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
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText

class RateRestoranActivity : AppCompatActivity() {

    lateinit var editTextPolosa: ImageView
    lateinit var rateEditText: TextInputEditText
    lateinit var rateText: TextView
    lateinit var ratingBar: RatingBar
    lateinit var gotovoButton: MaterialButton
    lateinit var backButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rate_restoran)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editTextPolosa = findViewById<ImageView>(R.id.imageView2)
        rateEditText = findViewById<TextInputEditText>(R.id.rateEditText)
        rateText = findViewById<TextView>(R.id.rateText)
        ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        gotovoButton = findViewById<MaterialButton>(R.id.gotovoButton)
        backButton = findViewById<MaterialButton>(R.id.backButton)

        gotovoButton.setOnClickListener {
            finish()
            Toast.makeText(this, getString(R.string.thanksForRate), Toast.LENGTH_SHORT).show()
        }
        backButton.setOnClickListener { finish() }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            when (rating.toInt()) {
                5 -> {
                    rateText.text = getString(R.string.excellent)
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.green))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.green)
                    )
                    rateEditText.isVisible = false
                    editTextPolosa.isVisible = false
                }

                4 -> {
                    rateText.text = getString(R.string.good)
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.orange)
                    )
                    rateEditText.isVisible = false
                    editTextPolosa.isVisible = false
                }

                3 -> {
                    rateText.text = getString(R.string.notBad)
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.orange)
                    )
                    rateEditText.isVisible = true
                    editTextPolosa.isVisible = true
                }

                2 -> {
                    rateText.text = getString(R.string.bad)
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.red))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.red)
                    )
                    rateEditText.isVisible = true
                    editTextPolosa.isVisible = true
                }

                1 -> {
                    rateText.text = getString(R.string.terrible)
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.red))
                    ratingBar.progressTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.red)
                    )
                    rateEditText.isVisible = true
                    editTextPolosa.isVisible = true
                }

                else -> {
                    rateText.text = getString(R.string.rateUs)
                    rateText.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    rateText.isVisible = false
                    editTextPolosa.isVisible = false
                }
            }
        }
    }
}