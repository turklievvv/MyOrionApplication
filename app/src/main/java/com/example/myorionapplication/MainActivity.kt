package com.example.myorionapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = findViewById<TextInputEditText>(R.id.phoneEditText)
        val buttonNext = findViewById<MaterialButton>(R.id.nextButton)

        buttonNext.setOnClickListener {
            val phone = editText.text?.toString()?.trim().orEmpty()
            if (phone.length == 10) {
                startActivity(SmsActivity.getIntent(this, phone))
            } else {
                editText.error = getString(R.string.editTextErrorText)
            }
        }
    }
}
