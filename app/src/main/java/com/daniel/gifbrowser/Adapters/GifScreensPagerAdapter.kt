package com.daniel.gifbrowser.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daniel.gifbrowser.View.FavoritesFragment
import com.daniel.gifbrowser.View.TrendyGifsFragment

class GifScreensPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment = Fragment()
        if (position == 0) {
            fragment = TrendyGifsFragment()
        } else if (position == 1) {
            fragment = FavoritesFragment()
        }
        return fragment
    }


}