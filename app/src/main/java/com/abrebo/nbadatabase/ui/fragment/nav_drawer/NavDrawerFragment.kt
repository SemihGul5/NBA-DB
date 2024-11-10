package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentNavDrawerBinding


class NavDrawerFragment : Fragment() {
    private lateinit var binding:FragmentNavDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentNavDrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

}