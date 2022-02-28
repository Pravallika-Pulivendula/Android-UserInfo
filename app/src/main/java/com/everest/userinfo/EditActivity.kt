package com.everest.userinfo

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.everest.userinfo.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.emailET.text
        val phoneNumber = binding.phoneNumberET.text
        val pinCode = binding.pincodeET.text
        val layout = binding.layout
        val validateButton = binding.validateButton

        validateButton.setOnClickListener {
            if (areFieldsEmpty(layout) and isPhoneNumberValid(phoneNumber) and isPinCodeValid(
                    pinCode
                ) and isEmailValid(
                    email
                )
            ) {
                makeFieldsInvisible(layout)
            }
        }
    }

    private fun areFieldsEmpty(layout: ConstraintLayout): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                if (TextUtils.isEmpty(v.text)) {
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
        }
        return true
    }

    private fun makeFieldsInvisible(layout: ConstraintLayout) {
        
    }

    private fun isPhoneNumberValid(phoneNumber: Editable): Boolean {
        if (phoneNumber.length < 10) {
            Toast.makeText(this, "Phone number shouldn't be less than 10", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun isPinCodeValid(pinCode: Editable): Boolean {
        if (pinCode.length < 6) {
            Toast.makeText(this, "Pincode shouldn't be less than 6", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isEmailValid(email: Editable): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}