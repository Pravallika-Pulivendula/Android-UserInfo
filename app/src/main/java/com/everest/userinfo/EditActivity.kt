package com.everest.userinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.everest.userinfo.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var isEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.validateButton.setOnClickListener {
            validateData()
        }

        binding.confirmButton.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra(USERNAME, binding.usernameET.text.toString())
            intent.putExtra(EMAIL, binding.emailET.text.toString())
            intent.putExtra(PHONE_NUMBER, binding.phoneNumberET.text.toString())
            intent.putExtra(PINCODE, binding.pincodeET.text.toString())
            intent.putExtra(ADDRESS, binding.addressET.text.toString())
            startActivity(intent)
        }

        binding.cancelButton.setOnClickListener {
            editDetails(binding.layout)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("validate_button", binding.validateButton.visibility)
        outState.putInt("confirm_button", binding.confirmButton.visibility)
        outState.putInt("cancel_button", binding.cancelButton.visibility)
        outState.putBoolean("userName", binding.usernameET.isEnabled)
        outState.putBoolean("email", binding.emailET.isEnabled)
        outState.putBoolean("phno", binding.phoneNumberET.isEnabled)
        outState.putBoolean("pincode", binding.pincodeET.isEnabled)
        outState.putBoolean("address", binding.addressET.isEnabled)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.validateButton.visibility = savedInstanceState.getInt("validate_button")
        binding.cancelButton.visibility = savedInstanceState.getInt("cancel_button")
        binding.confirmButton.visibility = savedInstanceState.getInt("confirm_button")
        isEnabled = savedInstanceState.getBoolean("userName")
        if (!isEnabled) {
            setBackgroundResourceForEditText(binding.layout)
        }
        binding.usernameET.isEnabled = savedInstanceState.getBoolean("userName")
        binding.emailET.isEnabled = savedInstanceState.getBoolean("email")
        binding.phoneNumberET.isEnabled = savedInstanceState.getBoolean("phno")
        binding.pincodeET.isEnabled = savedInstanceState.getBoolean("pincode")
        binding.addressET.isEnabled = savedInstanceState.getBoolean("address")
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun setBackgroundResourceForEditText(layout: ConstraintLayout) {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                v.isEnabled = false
                v.setBackgroundResource(0)
                v.setPadding(2, 2, 2, 2)
                v.setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    private fun validateData() {
        if (areFieldsEmpty(binding.layout) && isEmailValid(
                binding.emailET.text.toString(),
            ) && isPhoneNumberValid(
                binding.phoneNumberET.text.toString()
            ) && isPinCodeValid(binding.pincodeET.text.toString())
        ) {
            confirmDetails(binding.layout)
        }
    }

    private fun confirmDetails(layout: ConstraintLayout) {
        val headerText = "Your Details!"
        setBackgroundResourceForEditText(binding.layout)
        binding.confirmButton.visibility = View.VISIBLE
        binding.cancelButton.visibility = View.VISIBLE
        binding.validateButton.visibility = View.INVISIBLE
        binding.headerTV.text = headerText
    }

    private fun editDetails(layout: ConstraintLayout) {
        val headerText = "Update the Profile"
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                v.isEnabled = true
                v.setBackgroundResource(R.drawable.rounded_border_edittext)
                v.setPadding(40, 45, 45, 40)
            }
            binding.addressET.setPadding(40, 45, 45, 170)
            binding.cancelButton.visibility = View.INVISIBLE
            binding.confirmButton.visibility = View.INVISIBLE
            binding.validateButton.visibility = View.VISIBLE
            binding.headerTV.text = headerText
        }
    }

    fun areFieldsEmpty(layout: ConstraintLayout): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                val fieldName = v.getTag().toString()
                if (TextUtils.isEmpty(v.text)) {
                    Toast.makeText(this, "$fieldName field is required", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
            }
        }
        return true
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        if (phoneNumber.length != 10) {
            Toast.makeText(this, "Phone number shouldn't be less than 10", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    fun isPinCodeValid(pinCode: String): Boolean {
        if (pinCode.length != 6) {
            Toast.makeText(this, "Pincode shouldn't be less than 6", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}