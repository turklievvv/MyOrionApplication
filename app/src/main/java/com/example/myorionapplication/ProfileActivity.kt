package com.example.myorionapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myorionapplication.databinding.ActivityProfileBinding
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    lateinit var firstName: String
    lateinit var number: String
    lateinit var phoneNumber: String
    lateinit var lastName: String
    lateinit var birthDate: String
    lateinit var pol: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        number = intent.getStringExtra(PHONE_NUMBER_KEY).orEmpty()
        phoneNumber = "+7$number"
        firstName = intent.getStringExtra(FIRST_NAME_KEY).orEmpty()
        lastName = intent.getStringExtra(LAST_NAME_KEY).orEmpty()
        birthDate = intent.getStringExtra(BIRTH_DATE_KEY).orEmpty()
        pol = intent.getStringExtra(POL_KEY).orEmpty()

        binding.numberedittext.setText(phoneNumber.toString())
        binding.firstName.setText(firstName)
        binding.lastName.setText(lastName)
        binding.birthDate.setText(birthDate)
        binding.poltext.setText(pol)

        binding.backButton.setOnClickListener { finish() }

        binding.saveButton.setOnClickListener {
            val newFirstName = binding.firstName.text.toString()
            val newLastName = binding.lastName.text.toString()
            val resultIntent = intent

            resultIntent.putExtra("newFirstName", newFirstName)
            resultIntent.putExtra("newLastName", newLastName)

            setResult(RESULT_OK, resultIntent)
            finish()
        }
        binding.logoutButton.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
            val negativeButton = dialogView.findViewById<MaterialButton>(R.id.negativeButton)
            val positiveButton = dialogView.findViewById<MaterialButton>(R.id.positiveButton)
            val dialog = AlertDialog.Builder(this).setView(dialogView).create()

            negativeButton.setOnClickListener {
                dialog.dismiss()
            }

            positiveButton.setOnClickListener {
                val intent = Intent(this, EnterPhoneNumberActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            dialog.show()
        }

    }

    companion object {
        private const val PHONE_NUMBER_KEY = "phoneNumber"
        private const val FIRST_NAME_KEY = "firstName"
        private const val LAST_NAME_KEY = "lastName"
        private const val BIRTH_DATE_KEY = "birthDate"
        private const val POL_KEY = "pol"

        private const val PHONE_NUMBER = "phoneNumber"
        private const val FIRST_NAME = "firstName"
        private const val LAST_NAME = "lastName"
        private const val BIRTH_DATE = "birthDate"
        private const val POL = "pol"
        fun getIntent(
            context: Context,
            phoneNumber: String,
            firstName: String,
            lastName: String,
            birthDate: String,
            pol: String
        ) = Intent(context, ProfileActivity::class.java).apply {
            putExtra(PHONE_NUMBER, phoneNumber)
            putExtra(FIRST_NAME, firstName)
            putExtra(LAST_NAME, lastName)
            putExtra(BIRTH_DATE, birthDate)
            putExtra(POL, pol)
        }

    }


}
