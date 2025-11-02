package com.example.myorionapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SmsActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var resendButton: Button
    private lateinit var number: TextView
    private lateinit var codeVerification: TextInputEditText

    private var phoneNumber: String = ""
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sms)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        phoneNumber = intent.getStringExtra(PHONE_NUMBER) ?: ""

        timerText = findViewById(R.id.timerText)
        resendButton = findViewById(R.id.resendButton)
        number = findViewById(R.id.number)
        codeVerification = findViewById(R.id.phoneEditText)

        number.text = "+7$phoneNumber"

        startCountdown()

        resendButton.setOnClickListener {
            resendButton.isVisible = false
            startCountdown()
        }

        findViewById<MaterialButton>(R.id.backButton).setOnClickListener {
            finish()
        }

        codeVerification.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 4) {
                startActivity(RegistrationActivity.getIntent(this, phoneNumber))
                finish()
            }
        }
    }

    private fun startCountdown() {
        countDownTimer?.cancel()

        val totalMillis = 30_000L // 30 секунд

        countDownTimer = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerText.text = getString(R.string.timerText,secondsLeft)
            }

            override fun onFinish() {
                timerText.text = ""
                resendButton.isVisible = true
            }
        }.start()
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }

    companion object {
        private const val PHONE_NUMBER = "phoneNumber"

        fun getIntent(context: Context, phoneNumber: String): Intent {
            return Intent(context, SmsActivity::class.java).apply {
                putExtra(PHONE_NUMBER, phoneNumber)
            }
        }
    }
}
