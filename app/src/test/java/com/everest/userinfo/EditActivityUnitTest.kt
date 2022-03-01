package com.everest.userinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class EditActivityUnitTest {

    @MockK
    private lateinit var editActivity: EditActivity

    @get:Rule
    val rule:TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        editActivity = EditActivity()
    }

    @Test
    fun shouldReturnTrueWhenPhoneNumberIsValid() {
        val phoneNumber = 9087612345
        val isValid:Boolean = editActivity.isPhoneNumberValid(phoneNumber.toString())
        assertTrue(isValid)
    }

}