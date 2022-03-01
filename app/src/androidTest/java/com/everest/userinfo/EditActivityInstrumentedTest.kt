package com.everest.userinfo

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditActivityInstrumentedTest {
    @get:Rule
    val activityScenario = ActivityScenarioRule(EditActivity::class.java)

    @Test
    fun getTextFromPreviousActivity() {
        Espresso.onView(withId(R.id.usernameET)).perform(ViewActions.typeText("Pravallika"))
        Espresso.onView(withId(R.id.emailET)).perform(ViewActions.typeText("pravallika@gmail.com"))
        Espresso.onView(withId(R.id.phoneNumberET))
            .perform(ViewActions.typeText(9178965432.toString()))
        Espresso.onView(withId(R.id.pincodeET)).perform(ViewActions.typeText(516390.toString()))
        Espresso.onView(withId(R.id.addressET)).perform(ViewActions.typeText("Pulivendula"));
        Espresso.onView(withId(R.id.validateButton)).perform(click())
        Espresso.onView(withId(R.id.usernameET))
            .check(ViewAssertions.matches(ViewMatchers.withText("Pravallika")))
        Espresso.onView(withId(R.id.emailET))
            .check(ViewAssertions.matches(ViewMatchers.withText("pravallika@gmail.com")))
        Espresso.onView(withId(R.id.phoneNumberET))
            .check(ViewAssertions.matches(ViewMatchers.withText(9178965432.toString())))
        Espresso.onView(withId(R.id.pincodeET))
            .check(ViewAssertions.matches(ViewMatchers.withText(516390.toString())))
        Espresso.onView(withId(R.id.addressET))
            .check(ViewAssertions.matches(ViewMatchers.withText("Pulivendula")))
    }

}