package com.abrebo.nbadatabase.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.databinding.PlayerItemBinding
import com.abrebo.nbadatabase.ui.fragment.TeamDetailFragmentDirections
import com.abrebo.nbadatabase.ui.fragment.nav_drawer.BestThreePointPlayersFragmentDirections
import com.abrebo.nbadatabase.ui.fragment.nav_drawer.FilterAndSortPlayersFragmentDirections
import com.abrebo.nbadatabase.ui.fragment.nav_drawer.Top100PlayerFragmentDirections
import com.abrebo.nbadatabase.ui.viewmodel.FilterAndSortViewModel
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel
import com.abrebo.nbadatabase.utils.PageType
import com.bumptech.glide.Glide

class TeamDetailAdapter(val context:Context,
                        private var players:List<Player>,
                        private val homeViewModel:HomeViewModel?,
                        private val filterViewModel:FilterAndSortViewModel?,
                        private val page:PageType,
                        private val att:String?
):RecyclerView.Adapter<TeamDetailAdapter.PlayerHolder>() {
    inner class PlayerHolder(val binding:PlayerItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val binding=PlayerItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PlayerHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val binding=holder.binding
        val player= players[position]
        Glide.with(context)
            .load(player.image_url)
            .into(binding.ivPlayerImage)

        binding.tvPlayerName.text=player.name
        binding.playerOverall.text=player.overallAttribute.toString()
        binding.threePoint.text=player.threePointShot.toString()
        binding.drivingDunk.text=player.drivingDunk.toString()
        binding.tvRank.text=(position+1).toString()+"."
        binding.tvPlayerDetails.text=player.position+
                " | "+ player.archetype+"| Age: "+
                (2024-player.birthdate.split(", ")[1].toInt())



        if (page==PageType.TEAM_DETAIL){
            homeViewModel?.setAttributesBackground(player.overallAttribute, binding.playerOverall)
            homeViewModel?.setAttributesBackground(player.threePointShot, binding.threePoint)
            homeViewModel?.setAttributesBackground(player.drivingDunk, binding.drivingDunk)
            binding.playerCard.setOnClickListener {
                val navDirection=TeamDetailFragmentDirections.actionTeamDetailFragmentToPlayerDetailFragment(player)
                Navigation.findNavController(it).navigate(navDirection)
            }
        }else if (page==PageType.BEST_THREE_POINT){
            filterViewModel?.setAttributesBackground(player.threePointShot, binding.threePoint)
            binding.playerOverall.visibility=View.GONE
            binding.drivingDunk.visibility=View.GONE
            binding.playerCard.setOnClickListener {
                val navDirection=BestThreePointPlayersFragmentDirections.actionBestThreePointPlayersFragmentToPlayerDetailFragment(player)
                Navigation.findNavController(it).navigate(navDirection)
            }
        }else if (page==PageType.TOP_100){
            filterViewModel?.setAttributesBackground(player.overallAttribute, binding.playerOverall)
            binding.threePoint.visibility=View.GONE
            binding.drivingDunk.visibility=View.GONE
            binding.playerCard.setOnClickListener {
                val navDirection=Top100PlayerFragmentDirections.actionTop100PlayerFragmentToPlayerDetailFragment(player)
                Navigation.findNavController(it).navigate(navDirection)
            }
        }else if (page==PageType.FILTER_AND_SORT){
            when (att!!){
                "overallAttribute" -> {
                    binding.playerOverall.text=player.overallAttribute.toString()
                    filterViewModel?.setAttributesBackground(player.overallAttribute, binding.playerOverall)
                }
                "closeShot" ->{
                    binding.playerOverall.text=player.closeShot.toString()
                    filterViewModel?.setAttributesBackground(player.closeShot, binding.playerOverall)
                }
                "midRangeShot" ->{
                    binding.playerOverall.text=player.midRangeShot.toString()
                    filterViewModel?.setAttributesBackground(player.midRangeShot, binding.playerOverall)
                }
                "threePointShot" -> {
                    binding.playerOverall.text=player.threePointShot.toString()
                    filterViewModel?.setAttributesBackground(player.threePointShot, binding.playerOverall)
                }
                "freeThrow" ->{
                    binding.playerOverall.text=player.freeThrow.toString()
                    filterViewModel?.setAttributesBackground(player.freeThrow, binding.playerOverall)
                }
                "shotIQ" -> {
                    binding.playerOverall.text=player.shotIQ.toString()
                    filterViewModel?.setAttributesBackground(player.shotIQ, binding.playerOverall)
                }
                "offensiveConsistency" ->{
                    binding.playerOverall.text=player.offensiveConsistency.toString()
                    filterViewModel?.setAttributesBackground(player.offensiveConsistency, binding.playerOverall)
                }
                "layup" -> {
                    binding.playerOverall.text=player.layup.toString()
                    filterViewModel?.setAttributesBackground(player.layup, binding.playerOverall)
                }
                "standingDunk" -> {
                    binding.playerOverall.text=player.standingDunk.toString()
                    filterViewModel?.setAttributesBackground(player.standingDunk, binding.playerOverall)
                }
                "drivingDunk" -> {
                    binding.playerOverall.text=player.drivingDunk.toString()
                    filterViewModel?.setAttributesBackground(player.drivingDunk, binding.playerOverall)
                }
                "postHook" -> {
                    binding.playerOverall.text=player.postHook.toString()
                    filterViewModel?.setAttributesBackground(player.postHook, binding.playerOverall)
                }
                "postFade" -> {
                    binding.playerOverall.text=player.postFade.toString()
                    filterViewModel?.setAttributesBackground(player.postFade, binding.playerOverall)
                }
                "postControl" -> {
                    binding.playerOverall.text=player.postControl.toString()
                    filterViewModel?.setAttributesBackground(player.postControl, binding.playerOverall)
                }
                "drawFoul" -> {
                    binding.playerOverall.text=player.drawFoul.toString()
                    filterViewModel?.setAttributesBackground(player.drawFoul, binding.playerOverall)
                }
                "hands" ->{
                    binding.playerOverall.text=player.hands.toString()
                    filterViewModel?.setAttributesBackground(player.hands, binding.playerOverall)
                }
                "interiorDefense" -> {
                    binding.playerOverall.text=player.interiorDefense.toString()
                    filterViewModel?.setAttributesBackground(player.interiorDefense, binding.playerOverall)
                }
                "perimeterDefense" -> {
                    binding.playerOverall.text=player.perimeterDefense.toString()
                    filterViewModel?.setAttributesBackground(player.perimeterDefense, binding.playerOverall)
                }
                "steal" ->{
                    binding.playerOverall.text=player.steal.toString()
                    filterViewModel?.setAttributesBackground(player.steal, binding.playerOverall)
                }
                "block" ->{
                    binding.playerOverall.text=player.block.toString()
                    filterViewModel?.setAttributesBackground(player.block, binding.playerOverall)
                }
                "helpDefenseIQ" -> {
                    binding.playerOverall.text=player.helpDefenseIQ.toString()
                    filterViewModel?.setAttributesBackground(player.helpDefenseIQ, binding.playerOverall)
                }
                "passPerception" ->{
                    binding.playerOverall.text=player.passPerception.toString()
                    filterViewModel?.setAttributesBackground(player.passPerception, binding.playerOverall)
                }
                "defensiveConsistency" -> {
                    binding.playerOverall.text=player.defensiveConsistency.toString()
                    filterViewModel?.setAttributesBackground(player.defensiveConsistency, binding.playerOverall)
                }
                "speed" -> {
                    binding.playerOverall.text=player.speed.toString()
                    filterViewModel?.setAttributesBackground(player.speed, binding.playerOverall)
                }
                "agility" -> {
                    binding.playerOverall.text=player.agility.toString()
                    filterViewModel?.setAttributesBackground(player.agility, binding.playerOverall)
                }
                "strength" -> {
                    binding.playerOverall.text=player.strength.toString()
                    filterViewModel?.setAttributesBackground(player.strength, binding.playerOverall)
                }
                "vertical" -> {
                    binding.playerOverall.text=player.vertical.toString()
                    filterViewModel?.setAttributesBackground(player.vertical, binding.playerOverall)
                }
                "stamina" -> {
                    binding.playerOverall.text=player.stamina.toString()
                    filterViewModel?.setAttributesBackground(player.stamina, binding.playerOverall)
                }
                "hustle" -> {
                    binding.playerOverall.text=player.hustle.toString()
                    filterViewModel?.setAttributesBackground(player.hustle, binding.playerOverall)
                }
                "overallDurability" -> {
                    binding.playerOverall.text=player.overallDurability.toString()
                    filterViewModel?.setAttributesBackground(player.overallDurability, binding.playerOverall)
                }
                "passAccuracy" -> {
                    binding.playerOverall.text=player.passAccuracy.toString()
                    filterViewModel?.setAttributesBackground(player.passAccuracy, binding.playerOverall)
                }
                "ballHandle" -> {
                    binding.playerOverall.text=player.ballHandle.toString()
                    filterViewModel?.setAttributesBackground(player.ballHandle, binding.playerOverall)
                }
                "speedWithBall" -> {
                    binding.playerOverall.text=player.speedWithBall.toString()
                    filterViewModel?.setAttributesBackground(player.speedWithBall, binding.playerOverall)
                }
                "passIQ" -> {
                    binding.playerOverall.text=player.passIQ.toString()
                    filterViewModel?.setAttributesBackground(player.passIQ, binding.playerOverall)
                }
                "passVision" -> {
                    binding.playerOverall.text=player.passVision.toString()
                    filterViewModel?.setAttributesBackground(player.passVision, binding.playerOverall)
                }
                "offensiveRebound" -> {
                    binding.playerOverall.text=player.offensiveRebound.toString()
                    filterViewModel?.setAttributesBackground(player.offensiveRebound, binding.playerOverall)
                }
                "defensiveRebound" -> {
                    binding.playerOverall.text=player.defensiveRebound.toString()
                    filterViewModel?.setAttributesBackground(player.defensiveRebound, binding.playerOverall)
                }
            }

            binding.threePoint.visibility=View.GONE
            binding.drivingDunk.visibility=View.GONE
            binding.playerCard.setOnClickListener {
                val navDirection=FilterAndSortPlayersFragmentDirections.actionFilterAndSortPlayersFragmentToPlayerDetailFragment(player)
                Navigation.findNavController(it).navigate(navDirection)
            }
        }

    }

}