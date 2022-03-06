package com.everest.userinfo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
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
            intent.putExtra(
                "userDetails",
                UserDetails(
                    binding.usernameET.text.toString(),
                    binding.emailET.text.toString(),
                    binding.phoneNumberET.text.toString(),
                    binding.pincodeET.text.toString(),
                    binding.addressET.text.toString()
                )
            )
            startActivity(intent)
        }

        binding.cancelButton.setOnClickListener {
            editDetails(binding.layout)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(VALIDATE_BUTTON, binding.validateButton.visibility)
        outState.putInt(BUTTONS_VISIBILITY, binding.buttonsVisibility.visibility)
        outState.putBoolean(USERNAME, binding.usernameET.isEnabled)
        outState.putBoolean(FOCUS_EDIT_TEXT, binding.editTextEnable.isEnabled)
        outState.putString(HEADER_TEXT, binding.headerTV.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.validateButton.visibility = savedInstanceState.getInt(VALIDATE_BUTTON)
        binding.buttonsVisibility.visibility = savedInstanceState.getInt(BUTTONS_VISIBILITY)
        binding.editTextEnable.isEnabled = savedInstanceState.getBoolean(FOCUS_EDIT_TEXT)
        isEnabled = savedInstanceState.getBoolean(USERNAME)
        binding.headerTV.text = savedInstanceState.getString(HEADER_TEXT)
        if (!isEnabled) {
            customizeEditText(binding.layout, false)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun customizeEditText(
        layout: ConstraintLayout,
        isEnabled: Boolean,
    ) {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                v.isEnabled = isEnabled
            }
        }
    }

    private fun confirmDetails() {
        customizeEditText(binding.layout, false)
        changeButtonVisibility(visibile = true)
        binding.headerTV.text = getString(R.string.details_header_text)
    }

    private fun changeButtonVisibility(visibile: Boolean) {
        binding.buttonsVisibility.isVisible = visibile
        binding.validateButton.isVisible = !visibile
    }

    private fun editDetails(layout: ConstraintLayout) {
        customizeEditText(binding.layout, true)
        changeButtonVisibility(visibile = false)
        binding.headerTV.text = getString(R.string.update_header_text)
    }

    fun getToastMessage(toastMessage: String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
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

    fun areFieldsEmpty(layout: ConstraintLayout): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                val fieldName = v.getTag().toString()
                if (TextUtils.isEmpty(v.text)) {
                    getToastMessage(fieldName + " " + getString(R.string.field_is_required))
                    return false
                }
            }
        }
        return true
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        if (phoneNumber.length != 10) {
            getToastMessage(getString(R.string.phone_number_toast_message))
            return false
        }
        return true
    }

    fun isPinCodeValid(pinCode: String): Boolean {
        if (pinCode.length != 6) {
            getToastMessage(getString(R.string.pincode_toast_message))
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
            getToastMessage(getString(R.string.email_toast_message))
            return false
        }
        return true
    }
}
