package com.abrebo.nbadatabase.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.databinding.PlayerItemBinding

class TeamDetailAdapter(val context:Context,val players:List<Player>):RecyclerView.Adapter<TeamDetailAdapter.PlayerHolder>() {
    inner class PlayerHolder(val binding:PlayerItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val binding=PlayerItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PlayerHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val binding=holder.binding
        val player=players.get(position)
        val imageResource = getImageResourceByName(player.imageUrl)
        binding.ivPlayerImage.setImageResource(imageResource)
        binding.tvPlayerName.text=player.name
        binding.playerOverall.text=player.overallAttribute.toString()
        binding.threePoint.text=player.threePointShot.toString()
        binding.drivingDunk.text=player.drivingDunk.toString()
        setAttributesBackground(player.overallAttribute, binding.playerOverall)
        setAttributesBackground(player.threePointShot, binding.threePoint)
        setAttributesBackground(player.drivingDunk, binding.drivingDunk)

    }

    private fun setAttributesBackground(attributeValue: Int, textView: TextView) {
        val backgroundResource = when {
            attributeValue > 85 -> R.drawable.overall_dark_green_background
            attributeValue in 80..85 -> R.drawable.overall_light_green_background
            attributeValue in 75..79 -> R.drawable.overall_dark_yellow_background
            attributeValue in 70..74 -> R.drawable.overall_light_gray_background
            attributeValue in 60..69 -> R.drawable.overall_orange_background
            else -> R.drawable.overall_dark_red_background
        }
        textView.setBackgroundResource(backgroundResource)
    }


    private fun getImageResourceByName(imageName: String): Int {
        val resourceId = context.resources?.getIdentifier(imageName, "drawable", context.packageName)
        return if (resourceId != 0) {
            resourceId ?: R.drawable.default_image
        } else {
            R.drawable.default_image
        }

    }
}