package com.abrebo.nbadatabase.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.data.model.Player
import com.abrebo.nbadatabase.databinding.PlayerItemBinding
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel

class TeamDetailAdapter(val context:Context,
                        val players:List<Player>,
                        val viewModel:HomeViewModel
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
        val player=players.get(position)
        val imageResource = getImageResourceByName(player.imageUrl)
        binding.ivPlayerImage.setImageResource(imageResource)
        binding.tvPlayerName.text=player.name
        binding.playerOverall.text=player.overallAttribute.toString()
        binding.threePoint.text=player.threePointShot.toString()
        binding.drivingDunk.text=player.drivingDunk.toString()
        viewModel.setAttributesBackground(player.overallAttribute, binding.playerOverall)
        viewModel.setAttributesBackground(player.threePointShot, binding.threePoint)
        viewModel.setAttributesBackground(player.drivingDunk, binding.drivingDunk)

        binding.tvRank.text=(position+1).toString()+"."
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