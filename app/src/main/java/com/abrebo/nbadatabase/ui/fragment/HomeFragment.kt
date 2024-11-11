package com.abrebo.nbadatabase.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.MainPageActivity
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.data.model.TeamItem
import com.abrebo.nbadatabase.databinding.FragmentHomeBinding
import com.abrebo.nbadatabase.ui.adapter.TeamAdapter
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel
import com.abrebo.nbadatabase.utils.BackPressUtils
import com.abrebo.nbadatabase.utils.PageType
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adView: AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:HomeViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        MobileAds.initialize(requireContext()) {}
        adView = AdView(requireContext())
        adView.adUnitId = "ca-app-pub-4667560937795938/8788267145"
        adView.setAdSize(AdSize.BANNER)
        binding.adView.removeAllViews()
        binding.adView.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BackPressUtils.setBackPressCallback(this, viewLifecycleOwner)

        viewModel.sortedTeams.observe(viewLifecycleOwner) { sortedTeams ->
            val adapter = TeamAdapter(requireContext(), sortedTeams,PageType.HOME,null)
            binding.recyclerViewTeams.adapter = adapter
        }


    }

}
