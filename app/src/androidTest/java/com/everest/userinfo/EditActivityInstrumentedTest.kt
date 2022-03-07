package com.everest.userinfo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditActivityInstrumentedTest {
    @get:Rule
    val activityScenario = ActivityScenarioRule(EditActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.usernameET)).perform(typeText("Pravallika"), closeSoftKeyboard())
        onView(withId(R.id.emailET)).perform(typeText("pravallika@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.phoneNumberET)).perform(typeText("9189934567"), closeSoftKeyboard())
        onView(withId(R.id.pincodeET)).perform(typeText("890765"), closeSoftKeyboard())
        onView(withId(R.id.addressET)).perform(typeText("Pulivendula"), closeSoftKeyboard())
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.everest.userinfo", appContext.packageName)
    }

    @Test
    fun shouldValidateDataAndAllowUserToEditEnteredDataWhenCancelButtonIsClicked() {

        onView(withId(R.id.validateButton)).perform(click())

        onView(withId(R.id.usernameET)).check(matches(isNotEnabled()))
        onView(withId(R.id.emailET)).check(matches(isNotEnabled()))
        onView(withId(R.id.phoneNumberET)).check(matches(isNotEnabled()))
        onView(withId(R.id.pincodeET)).check(matches(isNotEnabled()))
        onView(withId(R.id.addressET)).check(matches(isNotEnabled()))

        onView(withId(R.id.usernameET)).check(matches(withText("Pravallika")))
        onView(withId(R.id.emailET)).check(matches(withText("pravallika@gmail.com")))
        onView(withId(R.id.phoneNumberET)).check(matches(withText("9189934567")))
        onView(withId(R.id.pincodeET)).check(matches(withText("890765")))
        onView(withId(R.id.addressET)).check(matches(withText("Pulivendula")))

        onView(withId(R.id.cancelButton)).perform(click())

        onView(withId(R.id.usernameET)).check(matches(isEnabled()))
        onView(withId(R.id.emailET)).check(matches(isEnabled()))
        onView(withId(R.id.phoneNumberET)).check(matches(isEnabled()))
        onView(withId(R.id.pincodeET)).check(matches(isEnabled()))
        onView(withId(R.id.addressET)).check(matches(isEnabled()))

    }

    @Test
    fun shouldValidateDataAndDisplayTheUserSummaryWhenConfirmButtonIsClicked() {

        onView(withId(R.id.validateButton)).perform(click())

        onView(withId(R.id.usernameET)).check(matches(isNotEnabled()))
        onView(withId(R.id.emailET)).check(matches(isNotEnabled()))
        onView(withId(R.id.phoneNumberET)).check(matches(isNotEnabled()))
        onView(withId(R.id.pincodeET)).check(matches(isNotEnabled()))
        onView(withId(R.id.addressET)).check(matches(isNotEnabled()))

        onView(withId(R.id.usernameET)).check(matches(withText("Pravallika")))
        onView(withId(R.id.emailET)).check(matches(withText("pravallika@gmail.com")))
        onView(withId(R.id.phoneNumberET)).check(matches(withText("9189934567")))
        onView(withId(R.id.pincodeET)).check(matches(withText("890765")))
        onView(withId(R.id.addressET)).check(matches(withText("Pulivendula")))

        onView(withId(R.id.confirmButton)).perform(click())

        val resultText =
            "Hi Pravallika, How are you? Are you staying at Pulivendula-890765. I am not able to contact you on 9189934567. Can I email you the details at pravallika@gmail.com"

        onView(withId(R.id.detailsTV)).check(matches(withText(resultText)))
    }

}