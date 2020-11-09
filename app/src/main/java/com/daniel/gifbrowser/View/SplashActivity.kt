package com.daniel.gifbrowser.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.daniel.gifbrowser.R
import java.lang.Exception

/**
 * The Class representing the Splash activity
 */

class SplashActivity : AppCompatActivity() {

    private lateinit var testo : TextView
    private lateinit var testo2 : TextView
    private val  timerHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        testo = findViewById(R.id.testo)
        testo2 = findViewById(R.id.testo2)
        val intent = Intent(this, MainActivity::class.java)
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                startActivity(intent)
            }
        }.start()
        startHandler()
        setupActionBar()
    }

    /**
     * calls crossFadeSplash method and calls it every 500 miliseconds
     */
    private val crossRunnable = object : Runnable {
        override fun run() {
            crossFadeSplash()
            timerHandler.postDelayed(this, 500)
        }
    }
    /**
     * initializes the timehandler for the animation
     */
    private fun startHandler(){
        timerHandler.post(crossRunnable)
    }
    /**
     * Sets up the bar color and title
     */
    fun setupActionBar(){
        val s = "Gif Browser"
        supportActionBar!!.setTitle(s)
        supportActionBar!!.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.gradient, null))
    }

    /**
     * Coordinates which View will be crossfaded.
     */
    private fun crossFadeSplash(){
        try{
            val view1 = testo
            val view2 = testo2
            if(view1.isShown){
                crossfade(view1,view2)
            }else{
                crossfade(view2,view1)
            }
        }catch(e: Exception){
        }
    }
    /**
     * Animates a crossfade between two views
     */
    private fun crossfade(view1: View, view2: View) {
        view2.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(500)
                .setListener(null)
        }
        view1.animate()
            .alpha(0f)
            .setDuration(500)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view1.visibility = View.INVISIBLE
                }
            })
    }
}
