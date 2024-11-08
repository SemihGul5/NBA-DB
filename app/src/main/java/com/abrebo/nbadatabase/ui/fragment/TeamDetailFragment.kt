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
import com.abrebo.nbadatabase.ui.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : Fragment() {
    private lateinit var binding: FragmentTeamDetailBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var players:ArrayList<Player>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        players=ArrayList()
        val team=TeamDetailFragmentArgs.fromBundle(requireArguments()).team
        viewModel.players.observe(viewLifecycleOwner){playerList->
            playerList.forEach {
                if (team.name == it.team) {
                    players.add(it)
                }
            }
            val firstEightPlayers =players.sortedByDescending { it.overallAttribute }.take(8)
            val totalOverall = firstEightPlayers.sumOf { it.overallAttribute }
            val ovr = totalOverall / firstEightPlayers.size


            val avgInsideScoring=firstEightPlayers.sumOf { it.layup+it.standingDunk+it.drivingDunk
                +it.postHook+it.postFade+it.postControl+it.drawFoul+it.hands}/8.0
            val avgOutsideScoring=firstEightPlayers.sumOf { it.closeShot+it.midRangeShot+it.threePointShot
            +it.freeThrow+it.shotIQ+it.offensiveConsistency}/6.0
            val ins=((avgOutsideScoring+avgInsideScoring)/2.0)/8





            binding.ovrText.text=ovr.toString()
            binding.insText.text=ins.toString()
            val adapter=TeamDetailAdapter(requireContext(),players)
            binding.teamPlayersRecyclerView.adapter=adapter
        }

        binding.teamLogoImageView.setImageResource(team.logo)
        binding.teamNameTextView.text=team.name
        binding.tierText.text="T3"//veri alınacak






    }



}

