package com.example.myorionapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myorionapplication.databinding.ActivityProfileBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
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

        val number = intent.getStringExtra("phoneNumber")
        val phoneNumber = "+7$number"
        val name = intent.getStringExtra("name")
        val firstName = intent.getStringExtra("firstName")
        val birthDate = intent.getStringExtra("birthDate")
        val pol = intent.getStringExtra("pol")

        binding.numberedittext.setText(phoneNumber.toString())
        binding.name.setText(name)
        binding.firstName.setText(firstName)
        binding.birthDate.setText(birthDate)
        binding.poltext.setText(pol)

        binding.backButton.setOnClickListener { finish() }

        binding.saveButton.setOnClickListener {
            val newName = binding.name.text.toString()
            val newFirstName = binding.firstName.text.toString()

            val resultIntent = intent
            resultIntent.putExtra("newName", newName)
            resultIntent.putExtra("newFirstName", newFirstName)

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
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                dialog.dismiss()
            }

            dialog.show()

        }

    }

    companion object {
        private const val PHONE_NUMBER = "phoneNumber"
        private const val NAME = "name"
        private const val FIRST_NAME = "firstName"
        private const val BIRTH_DATE = "birthDate"
        private const val POL = "pol"
        fun getIntent(
            context: Context,
            phoneNumber: String,
            name: String,
            firstName: String,
            birthDate: String,
            pol: String
        ) = Intent(context, ProfileActivity::class.java).apply {
            putExtra(PHONE_NUMBER, phoneNumber)
            putExtra(NAME, name)
            putExtra(FIRST_NAME, firstName)
            putExtra(BIRTH_DATE, birthDate)
            putExtra(POL, pol)
        }

    }


}
