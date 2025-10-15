package com.example.myorionapplication

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.mukeshsolanki.OTP_VIEW_TYPE_BORDER
import com.mukeshsolanki.OtpView

class SmsActivity : AppCompatActivity() {
    private lateinit var timerText: TextView
    private lateinit var resendButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sms)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<MaterialButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }


        val phoneNumber = intent.getStringExtra("phoneNumber")

        val number = findViewById<TextView>(R.id.number)
        number.text = "+7$phoneNumber"

        timerText = findViewById(R.id.timerText)
        resendButton = findViewById(R.id.resendButton)

        startCountdown()

        resendButton.setOnClickListener {
            resendButton.visibility = View.GONE
            startCountdown()

        }

        val codeVerification = findViewById<TextInputEditText>(R.id.phoneEditText)
        codeVerification.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                if (input.length == 4) {
                    startActivity(Intent(this@SmsActivity, RegistrationActivity::class.java))
                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun startCountdown() {
        val totalMillis = 10_000L // 30 секунд

        object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerText.text = "Отправить код снова через $secondsLeft секунд"
            }

            override fun onFinish() {
                timerText.text = ""
                resendButton.visibility = View.VISIBLE
            }
        }.start()
    }
}