package com.example.myorionapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SmsActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var resendButton: Button
    private lateinit var number: TextView
    private lateinit var codeVerification: TextInputEditText
    private lateinit var backButton: MaterialButton
    private var phoneNumber: String = ""
    private var timerJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sms)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        phoneNumber = intent.getStringExtra(PHONE_NUMBER).orEmpty()

        timerText = findViewById(R.id.timerText)
        resendButton = findViewById(R.id.resendButton)
        number = findViewById(R.id.number)
        codeVerification = findViewById(R.id.phoneEditText)
        backButton = findViewById<MaterialButton>(R.id.backButton)

        number.text = "+7$phoneNumber"

        startCountdown()

        resendButton.setOnClickListener {
            resendButton.isVisible = false
            timerJob?.cancel()
            startCountdown()
        }

        backButton.setOnClickListener {
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
        timerJob =
            lifecycleScope.startCountDownTimer(startTime = 60_000L, period = 1000L, start = { _ ->
                withContext(Dispatchers.Main) {
                    resendButton.isVisible = false
                    timerText.isVisible = true
                }
            }, tick = { time ->
                withContext(Dispatchers.Main) {
                    val seconds = time / 1000
                    timerText.text = "Отправить повторно через ${seconds}s"
                }
            }, end = { _ ->
                withContext(Dispatchers.Main) {
                    resendButton.isVisible = true
                    timerText.isVisible = false
                }
            })
    }

    override fun onDestroy() {
        timerJob?.cancel()
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
