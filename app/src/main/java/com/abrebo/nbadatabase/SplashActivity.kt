package com.abrebo.nbadatabase

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.abrebo.nbadatabase.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.imageView.startAnimation(fadeIn)


        fadeIn.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {
            }

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                navigateToMainActivity()
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {
            }
        })
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@SplashActivity, MainPageActivity::class.java)
        startActivity(intent)
        finish()
    }
}
