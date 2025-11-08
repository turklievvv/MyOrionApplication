package com.example.myorionapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myorionapplication.Notifications.NotificationsActvity
import com.example.myorionapplication.databinding.ActivityGalvnoeMenuBinding
import com.google.android.material.snackbar.Snackbar
import androidx.core.net.toUri
import com.example.myorionapplication.PayStories.PayStoryActivity


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
        var phoneNumber = intent.getStringExtra("phoneNumber")
        var name = intent.getStringExtra("name")
        var firstname = intent.getStringExtra("firstName")
        var birthDate = intent.getStringExtra("birthDate")
        val pol = intent.getStringExtra("pol")
        val profileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                name = data?.getStringExtra("newName")
                firstname = data?.getStringExtra("newFirstName")
            }
        }
        binding.instRestoran1.setOnClickListener {
            val url = "https://www.instagram.com/restaurant_pirosmani/"
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
        binding.instRestoran2.setOnClickListener {
            val url = "https://www.instagram.com/resto.quadrat/"
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
        binding.instRestoran3.setOnClickListener {
            val url = "https://www.instagram.com/dolce.salato/"
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
        binding.callRestoranButton1.setOnClickListener {
            val phoneNumber = "tel:+79634113232"
            val intent = Intent(Intent.ACTION_DIAL, phoneNumber.toUri())
            startActivity(intent)
        }
        binding.callRestoranButton2.setOnClickListener {
            val phoneNumber = "tel:+79187770909"
            val intent = Intent(Intent.ACTION_DIAL, phoneNumber.toUri())
            startActivity(intent)
        }
        binding.callRestoranButton3.setOnClickListener {
            val phoneNumber = "tel:+79640118668"
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
                    name.toString(),
                    firstname.toString(),
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
        ) = Intent(context, GalvnoeMenuActivity::class.java).apply {
            putExtra(PHONE_NUMBER, phoneNumber)
            putExtra(NAME, name)
            putExtra(FIRST_NAME, firstName)
            putExtra(BIRTH_DATE, birthDate)
            putExtra(POL, pol)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

    }

}