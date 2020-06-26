package com.zisa.guess

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest() {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong() {
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secrent

        for(n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView(withId(R.id.button)).perform(click())
                var message = resources.getString(R.string.Yes_you_got_it)
                if (n < secret) {
                    message = resources.getString(R.string.Bigger)
                }
                else if (n > secret) {
                    message = resources.getString(R.string.Smaller)
                }

                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.OK))).perform(click())
                //androidx.test.espresso.PerformException: Error performing 'single click - At Coordinates: 1285, 2557 and precision: 16, 16' on view 'Animations or transitions are enabled on the target device.
                //Caused by: androidx.test.espresso.PerformException: Error performing 'Send down motion event' on view 'unknown'.
                onView(withId(R.id.ed_number)).perform(closeSoftKeyboard());
                onView(withId(R.id.fab)).perform(click())
                onView(withText("Are you sure?")).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.Replay_OK))).perform(click())
            }
        }
    }
}