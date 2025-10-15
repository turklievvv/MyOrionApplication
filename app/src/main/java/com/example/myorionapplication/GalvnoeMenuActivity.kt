package com.example.myorionapplication

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myorionapplication.databinding.ActivityGalvnoeMenuBinding
import com.google.android.material.snackbar.Snackbar

class GalvnoeMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalvnoeMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGalvnoeMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.faq.setOnClickListener {
           Snackbar.make(binding.faq, "Это твои накопленные баллы за покупки", Snackbar.LENGTH_SHORT).show()
        }
        binding.pointsPayButton.setOnClickListener { startActivity(Intent(this, QRCodeActivity::class.java)) }

    }
}