package com.everest.userinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {

    private val _isValid = MutableLiveData(false)
    var isValid: LiveData<Boolean> = _isValid

    private val _visibility = MutableLiveData(false)
    var visibility: LiveData<Boolean> = _visibility

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

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
        this._userDetails.postValue(UserDetails(username, email, phoneNumber, pincode, address))
    }

}