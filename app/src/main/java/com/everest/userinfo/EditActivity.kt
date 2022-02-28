package com.everest.userinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.everest.userinfo.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var confirmButton: Button
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var headerTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userNameET = binding.usernameET
        val emailET = binding.emailET
        val phoneNumberET = binding.phoneNumberET
        val pinCodeET = binding.pincodeET
        val addressET = binding.addressET
        val layout = binding.layout
        validateButton = binding.validateButton
        confirmButton = binding.confirmButton
        cancelButton = binding.cancelButton
        headerTV = binding.headerTV

        validateButton.setOnClickListener {
            if (areFieldsEmpty(layout) && isEmailValid(emailET) && isPhoneNumberValid(
                    phoneNumberET
                ) && isPinCodeValid(pinCodeET)
            ) {
                makeFieldsInvisible(layout)
            }
        }

        confirmButton.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra(USERNAME, userNameET.text.toString())
            intent.putExtra(EMAIL, emailET.text.toString())
            intent.putExtra(PHONE_NUMBER, phoneNumberET.text.toString())
            intent.putExtra(PINCODE, pinCodeET.text.toString())
            intent.putExtra(ADDRESS, addressET.text.toString())
            startActivity(intent)
        }
    }

    private fun areFieldsEmpty(layout: ConstraintLayout): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                val fieldName = v.getTag().toString()
                if (TextUtils.isEmpty(v.text)) {
                    Toast.makeText(this, "$fieldName field is required", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
        }
        return true
    }

    private fun isPhoneNumberValid(phoneNumber: EditText): Boolean {
        if (phoneNumber.length() != 10) {
            Toast.makeText(this, "Phone number shouldn't be less than 10", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun isPinCodeValid(pinCode: EditText): Boolean {
        if (pinCode.length() != 6) {
            Toast.makeText(this, "Pincode shouldn't be less than 6", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isEmailValid(email: EditText): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun makeFieldsInvisible(layout: ConstraintLayout) {
        val headerText = "Your Details!"
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                v.isEnabled = false
                v.setBackgroundResource(0)
                v.setPadding(2, 2, 2, 2)
                v.setTextColor(Color.parseColor("#000000"))
            }
        }
        confirmButton.visibility = View.VISIBLE
        cancelButton.visibility = View.VISIBLE
        validateButton.visibility = View.INVISIBLE
        headerTV.text = headerText
    }

}