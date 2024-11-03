package com.sumanthacademy.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumanthacademy.myapplication.R


/**
 * A simple [Fragment] subclass.
 * Use the [IntroThirdScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntroThirdScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_intro_third_screen, container, false)
        return view
    }

    companion object {
        const val TAG = "IntroThirdScreenFragment"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IntroThirdScreenFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}