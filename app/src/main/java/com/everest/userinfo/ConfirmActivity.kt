package com.everest.userinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
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


        val resultText =
            "Hi <b>$userName</b>, How are you? Are you staying at <b>$address-$pincode</b>. I am not able to contact you on <b>$phoneNumber</b>. Can I email you the details at <b>$email</b>"

        binding.detailsTV.text = HtmlCompat.fromHtml(resultText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}