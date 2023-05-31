package com.example.myapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()  // This hides the ActionBar
        setContentView(R.layout.activity_splash_screen)

        val logo: ImageView = findViewById(R.id.logo)

        // Get screen size
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        // Account for size of the logo
        val logoSize = resources.getDimensionPixelSize(R.dimen.logo_size)
        val xMax = width - logoSize
        val yMax = height - logoSize

        val animatorSet = AnimatorSet()

        // move from top left to bottom right
        val anim1 = ObjectAnimator.ofFloat(logo, "translationX", 0f, xMax.toFloat())
        val anim2 = ObjectAnimator.ofFloat(logo, "translationY", 0f, yMax.toFloat())
        val set1 = AnimatorSet()
        set1.playTogether(anim1, anim2)

        // move from bottom right to bottom left
        val anim3 = ObjectAnimator.ofFloat(logo, "translationX", xMax.toFloat(), 0f)
        val set2 = AnimatorSet()
        set2.play(anim3)

        // move from bottom left to top right
        val anim4 = ObjectAnimator.ofFloat(logo, "translationX", 0f, xMax.toFloat())
        val anim5 = ObjectAnimator.ofFloat(logo, "translationY", yMax.toFloat(), 64f)
        val set3 = AnimatorSet()
        set3.playTogether(anim4, anim5)

        // move from top right to top left
        val anim6 = ObjectAnimator.ofFloat(logo, "translationX", xMax.toFloat(), xMax.toFloat() / 2)
        val set4 = AnimatorSet()
        set4.play(anim6)

        animatorSet.playSequentially(set1, set2, set3, set4)
        animatorSet.duration = 1000
        animatorSet.start()

        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                finish()
            }
        })
    }
}