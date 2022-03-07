package com.everest.userinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ValidatorUnitTest {
    @MockK
    private lateinit var editActivity: EditActivity

    @MockK
    private lateinit var validator: Validator

    @get:Rule
    val rule:TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        editActivity = EditActivity()
        validator = Validator()
    }

    @Test
    fun shouldReturnTrueWhenPhoneNumberIsValid() {
        val phoneNumber = 9087612345
        val isValid:Boolean = validator.isPhoneNumberValid(phoneNumber.toString(),editActivity)
        assertTrue(isValid)
    }

    @Test
    fun shouldReturnTrueWhenPincodeIsValid(){
        val pincode = 987663
        val isValid:Boolean = validator.isPinCodeValid(pincode.toString(),editActivity)
        assertTrue(isValid)
    }

    @Test
    fun shouldReturnTrueWhenEmailIsValid(){
        val email = "pravallika@gmail.com"
        val isValid:Boolean = validator.isEmailValid(email,editActivity)
        assertTrue(isValid)
    }
}