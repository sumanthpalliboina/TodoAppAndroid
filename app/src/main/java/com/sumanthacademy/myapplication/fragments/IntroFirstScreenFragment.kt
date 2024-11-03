package com.sumanthacademy.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sumanthacademy.myapplication.R

class IntroFirstScreenFragment:Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_design,container,false)
    }

    companion object {
        const val TAG = "IntroFirstScreenFragment"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IntroSecondScreenFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}