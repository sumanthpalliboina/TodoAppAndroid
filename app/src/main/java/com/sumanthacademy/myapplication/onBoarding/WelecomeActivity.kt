package com.sumanthacademy.myapplication.onBoarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.sumanthacademy.myapplication.MainActivity
import com.sumanthacademy.myapplication.R
import com.sumanthacademy.myapplication.adapter.IntroSliderAdapter

import com.sumanthacademy.myapplication.databinding.ActivityWelecomeBinding
import com.sumanthacademy.myapplication.fragments.IntroFirstScreenFragment
import com.sumanthacademy.myapplication.fragments.IntroSecondScreenFragment
import com.sumanthacademy.myapplication.fragments.IntroThirdScreenFragment
import com.sumanthacademy.myapplication.global.BaseActivity

class WelecomeActivity : BaseActivity(), View.OnClickListener {

    lateinit var welecomeBinding: ActivityWelecomeBinding
    private var fragmentsList = ArrayList<Fragment>()

    val changeCallback = object: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            welecomeBinding.pageIndicatorView?.selection = position
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        welecomeBinding = ActivityWelecomeBinding.inflate(layoutInflater)
        welecomeBinding?.root?.let { setContentView(it) }
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val w:Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val adapter = IntroSliderAdapter(this)
        welecomeBinding.viewPager.adapter = adapter

        fragmentsList.clear()
        fragmentsList.addAll(
            listOf(
                IntroFirstScreenFragment(),
                IntroSecondScreenFragment(),
                IntroThirdScreenFragment()
            )
        )
        adapter.setFragmentList(fragmentsList)

        welecomeBinding.pageIndicatorView.count = adapter.itemCount
        welecomeBinding.pageIndicatorView.setInteractiveAnimation(true)

        welecomeBinding.viewPager.registerOnPageChangeCallback(changeCallback)

        setListeners()
    }

    fun setListeners(){
        welecomeBinding.loginBtn.setOnClickListener(this)
        welecomeBinding.registerBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view?.id) {
                R.id.loginBtn -> {
                    val intent = Intent(this@WelecomeActivity,MainActivity::class.java)
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            startActivity(intent)
                            finish()
                        }, 500.toLong()
                    )
                }
                R.id.registerBtn -> {
                    Toast.makeText(this,"Under Implementation",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}