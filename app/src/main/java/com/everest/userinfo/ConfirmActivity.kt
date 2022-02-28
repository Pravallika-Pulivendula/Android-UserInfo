package com.everest.userinfo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.everest.userinfo.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmBinding
    private lateinit var detailsTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsTV = binding.detailsTV
        val userName = intent.getStringExtra(USERNAME)
        val email = intent.getStringExtra(EMAIL)
        val phoneNumber = intent.getStringExtra(PHONE_NUMBER)
        val pincode = intent.getStringExtra(PINCODE)
        val address = intent.getStringExtra(ADDRESS)

        val resultText =
            "Hi $userName, How are you? Are you staying at $address-$pincode. I am not able to contact you on $phoneNumber. Can I email you the details at $email"

        detailsTV.text = resultText
    }
}