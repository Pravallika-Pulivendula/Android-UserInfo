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

        val data: Bundle? = intent.extras
        val userDetails: UserDetails? = data?.getParcelable("userDetails");

        binding.detailsTV.text =
            getString(
                R.string.resultText,
                userDetails?.userName,
                userDetails?.address,
                userDetails?.pincode,
                userDetails?.phoneNumber,
                userDetails?.email
            )
    }

}