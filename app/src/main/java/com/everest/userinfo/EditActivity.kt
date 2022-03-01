package com.everest.userinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.everest.userinfo.databinding.ActivityEditBinding


@SuppressLint("SetTextI18n")
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
        outState.putInt("visibility", binding.validateButton.visibility)

        outState.putInt("confirm_button", binding.confirmButton.visibility)
        outState.putInt("cancel_button", binding.cancelButton.visibility)
        outState.putInt("userNameET", binding.usernameET.visibility)
        outState.putInt("emailET", binding.emailET.visibility)
        outState.putInt("phoneNumberET", binding.phoneNumberET.visibility)
        outState.putInt("pincodeET", binding.pincodeET.visibility)
        outState.putInt("addressET", binding.addressET.visibility)

        outState.putInt("userNameTV", binding.usernameTV.visibility)
        outState.putInt("emailTV", binding.emailTV.visibility)
        outState.putInt("phoneNumberTV", binding.phoneNumberTV.visibility)
        outState.putInt("pincodeTV", binding.pincodeTV.visibility)
        outState.putInt("addressTV", binding.addressTV.visibility)

        outState.putString("userName", binding.usernameET.text.toString())
        outState.putString("email", binding.emailET.text.toString())
        outState.putString("phoneNumber", binding.phoneNumberET.text.toString())
        outState.putString("pincode", binding.pincodeET.text.toString())
        outState.putString("address", binding.addressET.text.toString())

        outState.putInt("savedUserNameTV", binding.savedUsernameTV.visibility)
        outState.putInt("savedEmailTV", binding.savedEmailTV.visibility)
        outState.putInt("savedPhoneNumberTV", binding.savedPhoneNumberTV.visibility)
        outState.putInt("savedPincodeTV", binding.savedPincodeTV.visibility)
        outState.putInt("savedAddressTV", binding.savedAddressTV.visibility)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.validateButton.visibility = savedInstanceState.getInt("validate_button")
        binding.cancelButton.visibility = savedInstanceState.getInt("cancel_button")
        binding.confirmButton.visibility = savedInstanceState.getInt("confirm_button")
        binding.usernameET.visibility = savedInstanceState.getInt("userNameET")
        binding.emailET.visibility = savedInstanceState.getInt("emailET")
        binding.phoneNumberET.visibility = savedInstanceState.getInt("phoneNumberET")
        binding.pincodeET.visibility = savedInstanceState.getInt("pincodeET")
        binding.addressET.visibility = savedInstanceState.getInt("addressET")

        binding.usernameTV.visibility = savedInstanceState.getInt("userNameTV")
        binding.emailTV.visibility = savedInstanceState.getInt("emailTV")
        binding.phoneNumberTV.visibility = savedInstanceState.getInt("phoneNumberTV")
        binding.pincodeTV.visibility = savedInstanceState.getInt("pincodeTV")
        binding.addressTV.visibility = savedInstanceState.getInt("addressTV")


        binding.savedUsernameTV.visibility = savedInstanceState.getInt("savedUserNameTV")
        binding.savedEmailTV.visibility = savedInstanceState.getInt("savedEmailTV")
        binding.savedPhoneNumberTV.visibility = savedInstanceState.getInt("savedPhoneNumberTV")
        binding.savedPincodeTV.visibility = savedInstanceState.getInt("savedPincodeTV")
        binding.savedAddressTV.visibility = savedInstanceState.getInt("savedAddressTV")

        val isVisibile: Boolean = savedInstanceState.getBoolean("visibility")

        binding.savedUsernameTV.text = "Name: " + savedInstanceState.getString("userName")
        binding.savedEmailTV.text = "Email: " + savedInstanceState.getString("email")
        binding.savedPhoneNumberTV.text =
            "Phone number: " + savedInstanceState.getString("phoneNumber")
        binding.savedPincodeTV.text = "Pincode: " + savedInstanceState.getString("pincode")
        binding.savedAddressTV.text = "Address: " + savedInstanceState.getString("address")

        super.onRestoreInstanceState(savedInstanceState)
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
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v.getVisibility() == View.VISIBLE) {
                v.visibility = View.INVISIBLE
            } else {
                v.visibility = View.VISIBLE
            }
        }
        binding.savedUsernameTV.text = "Name: " + binding.usernameET.text
        binding.savedEmailTV.text = "Email: " + binding.emailET.text
        binding.savedPhoneNumberTV.text = "Phone Number: " + binding.phoneNumberET.text
        binding.savedPincodeTV.text = "Pincode: " + binding.pincodeET.text
        binding.savedAddressTV.text = "Address: " + binding.addressET.text

    }

    private fun editDetails(layout: ConstraintLayout) {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v.getVisibility() == View.VISIBLE) {
                v.visibility = View.INVISIBLE
            } else {
                v.visibility = View.VISIBLE
            }
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