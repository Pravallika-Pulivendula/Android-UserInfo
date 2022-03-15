package com.everest.userinfo

import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _pincode = MutableLiveData<String>()
    val pincode: LiveData<String> = _pincode

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _isValid = MutableLiveData(false)
    var isValid: LiveData<Boolean> = _isValid

    private val _visibility = MutableLiveData(false)
    var visibility: LiveData<Boolean> = _visibility

    fun isDataValid(isValid: Boolean) {
        this._isValid.value = isValid
    }

    fun setVisibility(isVisible: Boolean) {
        this._visibility.value = isVisible
    }

    fun setUserData(
        username: String,
        email: String,
        phoneNumber: String,
        pincode: String,
        address: String
    ) {
        this._username.postValue(username)
        this._email.postValue(email)
        this._phoneNumber.postValue(phoneNumber)
        this._pincode.postValue(pincode)
        this._address.postValue(address)
    }

    fun changeVisibility(confirmAndCancelButtons: Group, validateButton: Button) {
        confirmAndCancelButtons.isVisible = visibility.value!!
        validateButton.isVisible = !visibility.value!!
    }

    fun customizeEditText(
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

}