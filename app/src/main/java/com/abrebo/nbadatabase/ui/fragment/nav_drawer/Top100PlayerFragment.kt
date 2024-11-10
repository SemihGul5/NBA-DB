package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentTop100PlayerBinding

class Top100PlayerFragment : Fragment() {
    private lateinit var binding:FragmentTop100PlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentTop100PlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

}