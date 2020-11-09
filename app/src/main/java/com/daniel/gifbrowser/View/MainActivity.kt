package com.daniel.gifbrowser.View

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.daniel.gifbrowser.Adapters.GifScreensPagerAdapter
import com.daniel.gifbrowser.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * The Class representing the main activity
 */
class MainActivity : FragmentActivity() {

    //private  lateinit var viewModel : MainActivityViewModel
    private lateinit var flFragmentPH : FrameLayout
    lateinit var vpFragments: ViewPager2
    private lateinit var  tabLayout : TabLayout
    private val pagerAdapter = GifScreensPagerAdapter(this)
    //private var currentlyDisplayed = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flFragmentPH = findViewById(R.id.fl_fragment_ph)
        vpFragments = findViewById(R.id.vp_fragments)
        tabLayout = findViewById(R.id.tab_layout)
        SetupViews()
    }

    /**
     * Sets up the view elements, Sets the titles for the tabs and initializes the adapter for the viewpager.
     */
    fun SetupViews(){
        vpFragments.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, vpFragments) { tab, position ->

            if(position == 0){
                tab.text = "Trending"
            }else if(position == 1){
                tab.text = "Favorites"
            }
        }.attach()
        vpFragments.setPageTransformer(ZoomOutPageTransformer())
    }

}
