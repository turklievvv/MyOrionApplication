package com.example.myorionapplication

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

class RegistrationActivity : AppCompatActivity() {
    private lateinit var nameEditText: TextInputEditText
    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var birthEditText: TextInputEditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var editText: List<TextInputEditText>
    private lateinit var phoneNumber: String

    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        phoneNumber = intent.getStringExtra("phoneNumber").toString()
        button = findViewById<MaterialButton>(R.id.nextButton)
        nameEditText = findViewById<TextInputEditText>(R.id.name)
        firstNameEditText = findViewById<TextInputEditText>(R.id.firstName)
        birthEditText = findViewById<TextInputEditText>(R.id.birthDate)
        radioGroup = findViewById<RadioGroup>(R.id.radioGroupRegistration)
        button.isClickable = false
        button.isFocusable = false
        button.setOnClickListener {
            var hasError = false

            if (firstNameEditText.text.toString().isBlank()) {
                findViewById<TextInputLayout>(R.id.FirstNameLayout).error =
                    "Обязательное для заполнения"
                hasError = true
            }

            if (nameEditText.text.toString().isBlank()) {
                findViewById<TextInputLayout>(R.id.nameLayout).error = "Обязательное для заполнения"
                hasError = true
            }

            if (birthEditText.text.toString().isBlank()) {
                findViewById<TextInputLayout>(R.id.BirthDateLayout).error =
                    "Обязательное для заполнения"
                hasError = true
            }


            if (!hasError) {
                if (radioGroup.checkedRadioButtonId == -1) {
                    Toast.makeText(this, "Выберите пол", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                    val radioButton = findViewById<RadioButton>(selectedRadioButtonId)

                    startActivity(
                        GalvnoeMenuActivity.GlavnoeMenuIntent(
                            this,
                            phoneNumber,
                            nameEditText.text.toString(),
                            firstNameEditText.text.toString(),
                            birthEditText.text.toString(),
                            radioButton.text.toString()
                        )

                    )
                    finish()
                }
            }
        }



        button.setBackgroundColor(Color.GRAY)
        editText = listOf(nameEditText, firstNameEditText, birthEditText)

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
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFormState()
            }

            override fun afterTextChanged(s: Editable?) {}
        }


        nameEditText.addTextChangedListener(textWatcher)
        firstNameEditText.addTextChangedListener(textWatcher)
        radioGroup.setOnCheckedChangeListener { _, _ -> checkFormState() }

        firstNameEditText.doOnTextChanged { _, _, _, _ ->
            findViewById<TextInputLayout>(R.id.FirstNameLayout).error = null
        }

        nameEditText.doOnTextChanged { _, _, _, _ ->
            findViewById<TextInputLayout>(R.id.nameLayout).error = null
        }

        birthEditText.doOnTextChanged { _, _, _, _ ->
            findViewById<TextInputLayout>(R.id.BirthDateLayout).error = null
        }


    }

    private fun checkFormState() {

        val allFieldsFilled = editText.all { it.text.toString().trim().isNotEmpty() }
        val isRadioSelected = radioGroup.checkedRadioButtonId != -1
        val enableButton = allFieldsFilled && isRadioSelected
        button.isEnabled = enableButton
        button.isClickable = enableButton
        button.isFocusable = enableButton
        button.setBackgroundColor(if (enableButton) Color.parseColor("#FF5722") else Color.GRAY)
    }

    companion object {
        const val PHONE_NUMBER = "phoneNumber"
        fun registrationIntent(context: Context, phoneNumber: String) =
            Intent(context, RegistrationActivity::class.java).apply {
                putExtra(PHONE_NUMBER, phoneNumber)

            }
    }
}