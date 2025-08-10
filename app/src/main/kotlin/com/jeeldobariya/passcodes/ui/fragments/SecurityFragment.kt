package com.jeeldobariya.passcodes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeeldobariya.passcodes.R

class SecurityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_security, container, false)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
