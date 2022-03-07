package com.everest.userinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class UserDetails(
    var userName: String,
    var email: String,
    var phoneNumber: String,
    var pincode: String,
    var address: String
) : Parcelable {
}