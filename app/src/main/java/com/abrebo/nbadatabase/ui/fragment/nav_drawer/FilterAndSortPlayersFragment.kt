package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentFilterAndSortPlayersBinding
import com.abrebo.nbadatabase.ui.adapter.TeamDetailAdapter
import com.abrebo.nbadatabase.ui.viewmodel.FilterAndSortViewModel
import com.abrebo.nbadatabase.utils.PageType
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterAndSortPlayersFragment : Fragment() {
    private lateinit var binding: FragmentFilterAndSortPlayersBinding
    private val viewModel: FilterAndSortViewModel by viewModels()
    private lateinit var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilterAndSortPlayersBinding.inflate(inflater, container, false)
        MobileAds.initialize(requireContext()) {}
        adView = AdView(requireContext())
        adView.adUnitId = "ca-app-pub-4667560937795938/7119036215"
        adView.setAdSize(AdSize.BANNER)
        binding.adView.removeAllViews()
        binding.adView.addView(adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, getPlayerAttributes())
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.threshold = 1
        binding.buttonApplyFilter.setOnClickListener {
            val selectedAttribute = binding.autoCompleteTextView.text.toString()

            if (getPlayerAttributes().contains(selectedAttribute)) {
                viewModel.filterAndSortPlayers(selectedAttribute)
            } else {
                Toast.makeText(requireContext(), "Please select a valid attribute", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.players.observe(viewLifecycleOwner) { players ->
            val selectedAttribute = binding.autoCompleteTextView.text.toString()
            val teamDetailAdapter = TeamDetailAdapter(
                requireContext(),
                players,
                null,
                viewModel,
                PageType.FILTER_AND_SORT,
                selectedAttribute
            )
            binding.recyclerViewPlayers.adapter = teamDetailAdapter
        }


    }


    private fun getPlayerAttributes(): List<String> {
        return listOf(
            "overallAttribute", "closeShot", "midRangeShot", "threePointShot", "freeThrow",
            "shotIQ", "offensiveConsistency", "layup", "standingDunk", "drivingDunk", "postHook",
            "postFade", "postControl", "drawFoul", "hands", "interiorDefense", "perimeterDefense",
            "steal", "block", "helpDefenseIQ", "passPerception", "defensiveConsistency", "speed",
            "agility", "strength", "vertical", "stamina", "hustle", "overallDurability",
            "passAccuracy", "ballHandle", "speedWithBall", "passIQ", "passVision",
            "offensiveRebound", "defensiveRebound"
        )
    }
}
