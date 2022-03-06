package com.everest.userinfo

import android.content.Context
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.regex.Pattern

class Validator {
    private val emailPattern: Pattern = Pattern.compile("^[A-za-z0-9_.-]+@[a-z]+\\.+(com|co.in)")

    fun areFieldsEmpty(layout: ConstraintLayout, context: Context): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                val fieldName = v.getTag().toString()
                if (TextUtils.isEmpty(v.text)) {
                    getToastMessage(
                        context,
                        fieldName + " " + context.getString(R.string.field_is_required)
                    )
                    return false
                }
            }
        }
        return true
    }

    fun isPhoneNumberValid(phoneNumber: String, context: Context): Boolean {
        if (phoneNumber.length != 10) {
            getToastMessage(context, context.getString(R.string.phone_number_toast_message))
            return false
        }
        return true
    }

    fun isPinCodeValid(pinCode: String, context: Context): Boolean {
        if (pinCode.length != 6) {
            getToastMessage(context, context.getString(R.string.pincode_toast_message))
            return false
        }
        return true
    }

    fun isEmailValid(email: String, context: Context): Boolean {
        if (!emailPattern.matcher(email).matches()) {
            getToastMessage(context, context.getString(R.string.email_toast_message))
            return false
        }
        return true
    }

    private fun getToastMessage(context: Context, toastMessage: String) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}