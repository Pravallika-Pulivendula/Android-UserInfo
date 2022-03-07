package com.everest.userinfo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.everest.userinfo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var validator: Validator
    private var isEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        validator = Validator()
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.validateButton.setOnClickListener {
            validateData()
        }

        binding.confirmButton.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra(
                USER_DETAILS,
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
        outState.putInt(BUTTONS_VISIBILITY, binding.buttonsVisibility.visibility)
        outState.putInt(VALIDATE_BUTTON, binding.validateButton.visibility)
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

    private fun displayDetails() {
        customizeEditText(binding.layout, false)
        changeButtonVisibility(visible = true)
        binding.headerTV.text = getString(R.string.details_header_text)
    }

    private fun changeButtonVisibility(visible: Boolean) {
        binding.buttonsVisibility.isVisible = visible
        binding.validateButton.isVisible = !visible
    }

    private fun editDetails(layout: ConstraintLayout) {
        customizeEditText(binding.layout, true)
        changeButtonVisibility(visible = false)
        binding.headerTV.text = getString(R.string.update_header_text)
    }

    private fun validateData() {
        if (validator.areFieldsEmpty(
                binding.layout,
                this@EditActivity
            ) && (validator.isUserNameValid(
                binding.usernameET.text.toString(),
                this@EditActivity
            )) && validator.isEmailValid(
                binding.emailET.text.toString(), this@EditActivity
            ) && validator.isPhoneNumberValid(
                binding.phoneNumberET.text.toString(), this@EditActivity
            ) && validator.isPinCodeValid(binding.pincodeET.text.toString(), this@EditActivity)
        ) {
            displayDetails()
        }
    }
}
