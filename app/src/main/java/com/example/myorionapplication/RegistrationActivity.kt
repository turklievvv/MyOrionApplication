package com.example.myorionapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar
import androidx.core.graphics.toColorInt

class RegistrationActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var birthEditText: TextInputEditText
    private lateinit var radioGroupRegistration: RadioGroup
    private lateinit var editTextRegistration: List<TextInputEditText>
    private lateinit var phoneNumber: String
    private lateinit var nextButton: Button

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        phoneNumber = intent.getStringExtra("phoneNumber").toString().orEmpty()
        nextButton = findViewById<MaterialButton>(R.id.nextButton)
        firstNameEditText = findViewById<TextInputEditText>(R.id.name)
        lastNameEditText = findViewById<TextInputEditText>(R.id.firstName)
        birthEditText = findViewById<TextInputEditText>(R.id.birthDate)
        radioGroupRegistration = findViewById<RadioGroup>(R.id.radioGroupRegistration)

        nextButton.isClickable = false
        nextButton.isFocusable = false

        nextButton.setOnClickListener {
            var hasError = false

            if (lastNameEditText.text.toString().isBlank()) {
                findViewById<TextInputLayout>(R.id.FirstNameLayout).error =
                    getString(R.string.editTextErrorText)
                hasError = true
            }
            if (firstNameEditText.text.toString().isBlank()) {
                findViewById<TextInputLayout>(R.id.nameLayout).error =
                    getString(R.string.editTextErrorText)
                hasError = true
            }
            if (birthEditText.text.toString().isBlank()) {
                findViewById<TextInputLayout>(R.id.BirthDateLayout).error =
                    getString(R.string.editTextErrorText)
                hasError = true
            }

            if (!hasError) {
                if (radioGroupRegistration.checkedRadioButtonId == -1) {
                    Toast.makeText(this, getString(R.string.chooseGender), Toast.LENGTH_SHORT).show()
                } else {
                    val selectedRadioButtonId = radioGroupRegistration.checkedRadioButtonId
                    val radioButton = findViewById<RadioButton>(selectedRadioButtonId)

                    startActivity(
                        MainMenuActivity.getIntent(
                            this,
                            phoneNumber,
                            firstNameEditText.text.toString(),
                            lastNameEditText.text.toString(),
                            birthEditText.text.toString(),
                            radioButton.text.toString()
                        )
                    )
                    finish()
                }
            }
        }

        nextButton.setBackgroundColor(Color.GRAY)
        editTextRegistration = listOf(firstNameEditText, lastNameEditText, birthEditText)

        birthEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this, { _, selectedYear, selectedMonth, selectedDay ->
                    val formatted = String.format(
                        "%02d.%02d.%04d", selectedDay, selectedMonth + 1, selectedYear
                    )
                    birthEditText.setText(formatted)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        radioGroupRegistration.setOnCheckedChangeListener { _, _ -> checkFormState() }

        lastNameEditText.doOnTextChanged { _, _, _, _ ->
            checkFormState()
            findViewById<TextInputLayout>(R.id.FirstNameLayout).error = null
        }

        firstNameEditText.doOnTextChanged { _, _, _, _ ->
            checkFormState()
            findViewById<TextInputLayout>(R.id.nameLayout).error = null
        }

        birthEditText.doOnTextChanged { _, _, _, _ ->
            findViewById<TextInputLayout>(R.id.BirthDateLayout).error = null
        }
    }

    private fun checkFormState() {
        val allFieldsFilled = editTextRegistration.all { it.text.toString().trim().isNotEmpty() }
        val isRadioSelected = radioGroupRegistration.checkedRadioButtonId != -1
        val enableButton = allFieldsFilled && isRadioSelected

        nextButton.isEnabled = enableButton
        nextButton.isClickable = enableButton
        nextButton.isFocusable = enableButton
        nextButton.setBackgroundColor(if (enableButton) getColor(R.color.orange) else getColor(R.color.gray))
    }

    companion object {
        private const val PHONE_NUMBER = "phoneNumber"
        fun getIntent(context: Context, phoneNumber: String): Intent {
            return Intent(context, RegistrationActivity::class.java).apply {
                putExtra(PHONE_NUMBER, phoneNumber)

            }
        }
    }
}