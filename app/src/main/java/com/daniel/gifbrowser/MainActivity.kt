package com.daniel.gifbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    //private  lateinit var viewModel : MainActivityViewModel
    private lateinit var flFragmentPH : FrameLayout
    //private var currentlyDisplayed = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flFragmentPH = findViewById(R.id.fl_fragment_ph)
        //viewModel = MainActivityViewModel()
        //viewModel.requestCOmmits()!!.observe(this, Observer<ArrayList<CommitDetail>> { posts -> showCommits(posts) })
        showTrendyGifsTemp()
    }

    fun showTrendyGifsTemp(){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val frag = TrendyGifsFragment()
        //frag.arguments = bundle
        fragmentTransaction.replace(R.id.fl_fragment_ph, frag,"fragment_ph_fl")
        fragmentTransaction.commit()
    }
}
