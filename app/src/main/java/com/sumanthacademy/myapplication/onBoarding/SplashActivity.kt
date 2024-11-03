package com.sumanthacademy.myapplication.onBoarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sumanthacademy.myapplication.util.AppConstants
import com.sumanthacademy.myapplication.MainActivity
import com.sumanthacademy.myapplication.R
import com.sumanthacademy.myapplication.global.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    override fun onStart() {
        super.onStart()
        goToNextScreen()
    }

    private fun goToNextScreen(){
        val intent = Intent(this@SplashActivity,WelecomeActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(intent)
                finish()
            }, AppConstants.SPLASH_TIME_OUT.toLong()
        )
    }
}