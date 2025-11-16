package com.example.myorionapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myorionapplication.notifications.NotificationsActvity
import com.example.myorionapplication.databinding.ActivityGalvnoeMenuBinding
import androidx.core.net.toUri
import com.example.myorionapplication.payStories.PayStoryActivity


class MainMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalvnoeMenuBinding
    lateinit var phoneNumber: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var birthDate: String
    lateinit var pol: String
    lateinit var profileLauncher: ActivityResultLauncher<Intent>

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

        phoneNumber = intent.getStringExtra(PHONE_NUMBER_KEY).orEmpty()
        firstName = intent.getStringExtra(FIRST_NAME_KEY).orEmpty()
        lastName = intent.getStringExtra(LAST_NAME_KEY).orEmpty()
        birthDate = intent.getStringExtra(BIRTH_DATE_KEY).orEmpty()
        pol = intent.getStringExtra(POL_KEY).orEmpty()

        profileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                firstName = data?.getStringExtra("newFirstName").orEmpty()
                lastName = data?.getStringExtra("newLastName").orEmpty()
            }
        }

        binding.instPirosmani.setOnClickListener {
            val url = getString(R.string.instPirosmani)
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
        binding.instBakariRestoran.setOnClickListener {
            val url = getString(R.string.instBakari)
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
        binding.instDolceVitoRestoran.setOnClickListener {

            val url = getString(R.string.instDolceVito)
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
        binding.callRestoranPirsomaniButton.setOnClickListener {
            val phoneNumber = getString(R.string.phonePirsomani)
            val intent = Intent(Intent.ACTION_DIAL, phoneNumber.toUri())
            startActivity(intent)
        }
        binding.callBakariRestoran.setOnClickListener {
            val phoneNumber = getString(R.string.phoneBakari)
            val intent = Intent(Intent.ACTION_DIAL, phoneNumber.toUri())
            startActivity(intent)
        }
        binding.callDocleVitoRestoran.setOnClickListener {
            val phoneNumber = getString(R.string.phoneDolceVito)
            val intent = Intent(Intent.ACTION_DIAL, phoneNumber.toUri())
            startActivity(intent)
        }


        binding.lastPaysButton.setOnClickListener {
            startActivity(Intent(this, PayStoryActivity::class.java))
        }
        binding.faq.setOnClickListener {
            startActivity(Intent(this, BonusSystemInfoActivity::class.java))
        }
        binding.pointsPayButton.setOnClickListener {
            startActivity(
                Intent(
                    this, QRCodeActivity::class.java
                )
            )
        }

        binding.profileButton.setOnClickListener {
            profileLauncher.launch(
                ProfileActivity.getIntent(
                    this,
                    phoneNumber.toString(),
                    firstName.toString(),
                    lastName.toString(),
                    birthDate.toString(),
                    pol.toString()
                )
            )
        }
        binding.notificationButton.setOnClickListener {
            startActivity(
                Intent(
                    this, NotificationsActvity::class.java
                )
            )
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
        ) = Intent(context, MainMenuActivity::class.java).apply {
            putExtra(PHONE_NUMBER, phoneNumber)
            putExtra(FIRST_NAME, firstName)
            putExtra(LAST_NAME, lastName)
            putExtra(BIRTH_DATE, birthDate)
            putExtra(POL, pol)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

    }

}