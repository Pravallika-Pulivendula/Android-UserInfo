package com.everest.userinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.everest.userinfo.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(USERNAME)
        val email = intent.getStringExtra(EMAIL)
        val phoneNumber = intent.getStringExtra(PHONE_NUMBER)
        val pincode = intent.getStringExtra(PINCODE)
        val address = intent.getStringExtra(ADDRESS)


        binding.detailsTV.text =
            getString(
                R.string.resultText,
                userName,
                address,
                pincode,
                phoneNumber,
                email
            )
    }

}