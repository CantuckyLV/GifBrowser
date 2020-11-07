package com.daniel.gifbrowser

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.viewpager2.widget.ViewPager2
import com.daniel.gifbrowser.View.FavoritesFragment
import com.daniel.gifbrowser.View.MainActivity
import com.daniel.gifbrowser.View.TrendyGifsFragment
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class MainActivityTest{

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testViewPagerIsShowing() {
        Espresso.onView(ViewMatchers.withId(R.id.vp_fragments)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
    }
    @Test
    fun testareFragmentsLoaded() {
        Thread.sleep(2000)
        Espresso.onView(allOf(ViewMatchers.withId(R.id.vp_fragments), isCompletelyDisplayed()))
            .check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
    @Test
    fun testFirstFragment() {
        //TODO Implement Idling Resource
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.rv_gifs)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
    }

    @Test
    fun testIsTrendingShowing() {
        //TODO Implement Idling Resource
        Thread.sleep(2000)
        val recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.rv_gifs)
        Assert.assertTrue(recyclerView.adapter!!.itemCount > 0)
    }
    @Test
    fun testOpenFavs() {
        //TODO Implement Idling Resource
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.vp_fragments)).perform(
            swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.rv_fav_gifs)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        val recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.rv_fav_gifs)
        Assert.assertTrue(recyclerView.adapter!!.itemCount > 0)
    }



}