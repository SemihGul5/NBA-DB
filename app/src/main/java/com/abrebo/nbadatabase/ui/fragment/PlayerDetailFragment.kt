package com.abrebo.nbadatabase.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentPlayerDetailBinding
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel
import com.abrebo.nbadatabase.utils.sumAllIntAttributes
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class PlayerDetailFragment : Fragment() {
    private lateinit var binding:FragmentPlayerDetailBinding
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentPlayerDetailBinding.inflate(inflater, container, false)
        MobileAds.initialize(requireContext()) {}

        // Setup Banner Ad
        adView = AdView(requireContext())
        adView.adUnitId = "ca-app-pub-4667560937795938/2377506116"
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
        val player=PlayerDetailFragmentArgs.fromBundle(requireArguments()).player
        //val imageResource = viewModel.getImageResourceByName(player.imageUrl)
        //binding.playerImageView.setImageResource(imageResource)
        Glide.with(requireContext())
            .load(player.image_url)
            .into(binding.playerImageView)

        binding.playerNameTextView.text=player.name
        binding.positionTextView.text="Pos: "+player.position
        binding.birthdateTextView.text="Brt: "+player.birthdate
        binding.archetypeTextView.text="Arc: "+player.archetype
        binding.teamTextView.text="Team: "+player.team
        binding.overallTextView.text="Ovr: "+player.overallAttribute.toString()
        viewModel.setAttributesBackground(player.overallAttribute,binding.overallTextView)
        val outsideScoring=((player.closeShot+player.midRangeShot+player.threePointShot+
                player.freeThrow+player.shotIQ+player.offensiveConsistency)/6.0).roundToInt()
        binding.outsideScoring.text=outsideScoring.toString()
        viewModel.setAttributesBackground(outsideScoring,binding.outsideScoring)
        binding.closeShot.text=player.closeShot.toString()
        viewModel.setAttributesBackground(player.closeShot,binding.closeShot)
        binding.midRangeShot.text=player.midRangeShot.toString()
        viewModel.setAttributesBackground(player.midRangeShot,binding.midRangeShot)
        binding.threePointShot.text=player.threePointShot.toString()
        viewModel.setAttributesBackground(player.threePointShot,binding.threePointShot)
        binding.freeThrow.text=player.freeThrow.toString()
        viewModel.setAttributesBackground(player.freeThrow,binding.freeThrow)
        binding.shotIq.text=player.shotIQ.toString()
        viewModel.setAttributesBackground(player.shotIQ,binding.shotIq)
        binding.offensiveConsistency.text=player.offensiveConsistency.toString()
        viewModel.setAttributesBackground(player.offensiveConsistency,binding.offensiveConsistency)
        //
        val athleticism=((player.speed+player.agility+player.strength+
                player.vertical+player.stamina+player.hustle+player.overallDurability)/7.0).roundToInt()
        binding.athleticism.text=athleticism.toString()
        viewModel.setAttributesBackground(athleticism,binding.athleticism)
        binding.speed.text=player.speed.toString()
        viewModel.setAttributesBackground(player.speed,binding.speed)
        binding.agility.text=player.agility.toString()
        viewModel.setAttributesBackground(player.agility,binding.agility)
        binding.strength.text=player.strength.toString()
        viewModel.setAttributesBackground(player.strength,binding.strength)
        binding.vertical.text=player.vertical.toString()
        viewModel.setAttributesBackground(player.vertical,binding.vertical)
        binding.stamina.text=player.stamina.toString()
        viewModel.setAttributesBackground(player.stamina,binding.stamina)
        binding.hustle.text=player.hustle.toString()
        viewModel.setAttributesBackground(player.hustle,binding.hustle)
        binding.overallDurability.text=player.overallDurability.toString()
        viewModel.setAttributesBackground(player.overallDurability,binding.overallDurability)
        //
        val insideScoring=((player.layup+player.standingDunk+player.drivingDunk+
                player.postHook+player.postFade+player.postControl+player.drawFoul+player.hands)/8.0).roundToInt()
        binding.insideScoring.text=insideScoring.toString()
        viewModel.setAttributesBackground(insideScoring,binding.insideScoring)
        binding.layup.text=player.layup.toString()
        viewModel.setAttributesBackground(player.layup,binding.layup)
        binding.standingDunk.text=player.standingDunk.toString()
        viewModel.setAttributesBackground(player.standingDunk,binding.standingDunk)
        binding.drivingDunk.text=player.drivingDunk.toString()
        viewModel.setAttributesBackground(player.drivingDunk,binding.drivingDunk)
        binding.postHook.text=player.postHook.toString()
        viewModel.setAttributesBackground(player.postHook,binding.postHook)
        binding.postFade.text=player.postFade.toString()
        viewModel.setAttributesBackground(player.postFade,binding.postFade)
        binding.postControl.text=player.postControl.toString()
        viewModel.setAttributesBackground(player.postControl,binding.postControl)
        binding.drawFoul.text=player.drawFoul.toString()
        viewModel.setAttributesBackground(player.drawFoul,binding.drawFoul)
        binding.hands.text=player.hands.toString()
        viewModel.setAttributesBackground(player.hands,binding.hands)
        //
        val playmarking=((player.passAccuracy+player.ballHandle+player.speedWithBall+
                player.passIQ+player.passVision)/5.0).roundToInt()
        binding.playmaking.text=playmarking.toString()
        viewModel.setAttributesBackground(playmarking,binding.playmaking)
        binding.passAccuracy.text=player.passAccuracy.toString()
        viewModel.setAttributesBackground(player.passAccuracy,binding.passAccuracy)
        binding.ballHandle.text=player.ballHandle.toString()
        viewModel.setAttributesBackground(player.ballHandle,binding.ballHandle)
        binding.speedWithBall.text=player.speedWithBall.toString()
        viewModel.setAttributesBackground(player.speedWithBall,binding.speedWithBall)
        binding.passIq.text=player.passIQ.toString()
        viewModel.setAttributesBackground(player.passIQ,binding.passIq)
        binding.passVision.text=player.passVision.toString()
        viewModel.setAttributesBackground(player.passVision,binding.passVision)
        //
        val defense=((player.interiorDefense+player.perimeterDefense+player.steal+
                player.block+player.helpDefenseIQ+player.passPerception+player.defensiveConsistency)/7.0).roundToInt()
        binding.defense.text=defense.toString()
        viewModel.setAttributesBackground(defense,binding.defense)
        binding.interiorDefense.text=player.interiorDefense.toString()
        viewModel.setAttributesBackground(player.interiorDefense,binding.interiorDefense)
        binding.perimeterDefense.text=player.perimeterDefense.toString()
        viewModel.setAttributesBackground(player.perimeterDefense,binding.perimeterDefense)
        binding.steal.text=player.steal.toString()
        viewModel.setAttributesBackground(player.steal,binding.steal)
        binding.block.text=player.block.toString()
        viewModel.setAttributesBackground(player.block,binding.block)
        binding.helpDefenseIq.text=player.helpDefenseIQ.toString()
        viewModel.setAttributesBackground(player.helpDefenseIQ,binding.helpDefenseIq)
        binding.passPerception.text=player.passPerception.toString()
        viewModel.setAttributesBackground(player.passPerception,binding.passPerception)
        binding.defensiveConsistency.text=player.defensiveConsistency.toString()
        viewModel.setAttributesBackground(player.defensiveConsistency,binding.defensiveConsistency)
        //
        val rebounding=((player.offensiveRebound+player.defensiveRebound)/2.0).roundToInt()
        binding.rebounding.text=rebounding.toString()
        viewModel.setAttributesBackground(rebounding,binding.rebounding)
        binding.offensiveRebound.text=player.offensiveRebound.toString()
        viewModel.setAttributesBackground(player.offensiveRebound,binding.offensiveRebound)
        binding.defensiveRebound.text=player.defensiveRebound.toString()
        viewModel.setAttributesBackground(player.defensiveRebound,binding.defensiveRebound)

        binding.totalAttributes.text=player.sumAllIntAttributes().toString()
        binding.totalAttributes.setBackgroundResource(R.drawable.blue_background)

        viewModel.updateRadarChart(player.overallAttribute,defense,insideScoring,
            rebounding,outsideScoring, playmarking, athleticism,binding.performanceChart)



    }

}