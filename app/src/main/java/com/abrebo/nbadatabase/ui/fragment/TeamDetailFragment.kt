package com.abrebo.nbadatabase.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.databinding.FragmentTeamDetailBinding
import com.abrebo.nbadatabase.ui.adapter.TeamDetailAdapter
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel
import com.abrebo.nbadatabase.utils.PageType
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : Fragment() {
    private lateinit var binding: FragmentTeamDetailBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adView: AdView
    private lateinit var players:ArrayList<Player>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        MobileAds.initialize(requireContext()) {}
        adView = AdView(requireContext())
        adView.adUnitId = "ca-app-pub-4667560937795938/8245221525"
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
        players=ArrayList()
        val team=TeamDetailFragmentArgs.fromBundle(requireArguments()).team
        viewModel.players.observe(viewLifecycleOwner){playerList->
            playerList.forEach {
                if (team.teamName == it.team) {
                    players.add(it)
                }
            }
            val adapter=TeamDetailAdapter(requireContext(),players,viewModel,null,PageType.TEAM_DETAIL,null)
            binding.teamPlayersRecyclerView.adapter=adapter
        }

        binding.teamLogoImageView.setImageResource(team.logoRes)
        binding.teamNameTextView.text=team.teamName

        viewModel.teamStats.observe(viewLifecycleOwner){teamStatsList->
            teamStatsList.forEach {
                if (it.team_name==team.teamName){
                    binding.tierText.text=it.tier
                    binding.ovrText.text=it.ovr
                    binding.insText.text=it.ins
                    binding.outText.text=it.out
                    binding.athText.text=it.ath
                    binding.plaText.text=it.pla
                    binding.defText.text=it.def
                    binding.rebText.text=it.reb
                    binding.intText.text=it.int
                    binding.potText.text=it.pot
                    viewModel.setAttributesBackground(it.ovr.toInt(), binding.ovrText)
                    viewModel.setAttributesBackground(it.ins.toInt(), binding.insText)
                    viewModel.setAttributesBackground(it.out.toInt(), binding.outText)
                    viewModel.setAttributesBackground(it.ath.toInt(), binding.athText)
                    viewModel.setAttributesBackground(it.pla.toInt(), binding.plaText)
                    viewModel.setAttributesBackground(it.def.toInt(), binding.defText)
                    viewModel.setAttributesBackground(it.reb.toInt(), binding.rebText)
                    viewModel.setAttributesBackground(it.int.toInt(), binding.intText)
                    viewModel.setAttributesBackground(it.pot.toInt(), binding.potText)

                }
            }
        }
    }
}

