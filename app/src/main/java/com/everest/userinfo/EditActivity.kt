package com.everest.userinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.everest.userinfo.databinding.ActivityEditBinding
import java.util.regex.Pattern

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var isEnabled: Boolean = false

    private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    )

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
        outState.putInt(VALIDATE_BUTTON, binding.validateButton.visibility)
        outState.putInt(CONFIRM_BUTTON, binding.confirmButton.visibility)
        outState.putInt(CANCEL_BUTTON, binding.cancelButton.visibility)
        outState.putBoolean(USERNAME, binding.usernameET.isEnabled)
        outState.putBoolean(EMAIL, binding.emailET.isEnabled)
        outState.putBoolean(PHONE_NUMBER, binding.phoneNumberET.isEnabled)
        outState.putBoolean(PINCODE, binding.pincodeET.isEnabled)
        outState.putBoolean(ADDRESS, binding.addressET.isEnabled)
        outState.putString(HEADER_TEXT, binding.headerTV.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.validateButton.visibility = savedInstanceState.getInt(VALIDATE_BUTTON)
        binding.cancelButton.visibility = savedInstanceState.getInt(CANCEL_BUTTON)
        binding.confirmButton.visibility = savedInstanceState.getInt(CONFIRM_BUTTON)
        isEnabled = savedInstanceState.getBoolean(USERNAME)
        if (!isEnabled) {
            customizeEditTextToDisplayEnteredDetails(binding.layout)
        }
        binding.usernameET.isEnabled = savedInstanceState.getBoolean(USERNAME)
        binding.emailET.isEnabled = savedInstanceState.getBoolean(EMAIL)
        binding.phoneNumberET.isEnabled = savedInstanceState.getBoolean(PHONE_NUMBER)
        binding.pincodeET.isEnabled = savedInstanceState.getBoolean(PINCODE)
        binding.addressET.isEnabled = savedInstanceState.getBoolean(ADDRESS)

        binding.headerTV.text = savedInstanceState.getString(HEADER_TEXT)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun customizeEditTextToDisplayEnteredDetails(layout: ConstraintLayout) {
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
            confirmDetails()
        }
    }

    private fun confirmDetails() {
        customizeEditTextToDisplayEnteredDetails(binding.layout)
        binding.confirmButton.visibility = View.VISIBLE
        binding.cancelButton.visibility = View.VISIBLE
        binding.validateButton.visibility = View.INVISIBLE
        binding.headerTV.text = DETAILS_HEADER_TEXT
    }

    private fun editDetails(layout: ConstraintLayout) {
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
            binding.headerTV.text = UPDATE_HEADER_TEXT
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
        if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}