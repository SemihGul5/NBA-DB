package com.abrebo.nbadatabase.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.nbadatabase.data.model.TeamItem
import com.abrebo.nbadatabase.databinding.TeamCardItemBinding
import com.abrebo.nbadatabase.ui.fragment.HomeFragmentDirections

class TeamAdapter(val context:Context,
                  val teamItemList:List<TeamItem>
):RecyclerView.Adapter<TeamAdapter.TeamHolder>() {
    inner class TeamHolder(val binding:TeamCardItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val binding=TeamCardItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return TeamHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamItemList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        val binding=holder.binding
        val team=teamItemList.get(position)
        binding.cardBackground.background=context.getDrawable(team.background)
        binding.logoImageView.setImageResource(team.logo)
        binding.teamNameTextView.text=team.name

        binding.cardBackground.setOnClickListener {
            val navDirection=HomeFragmentDirections.actionHomeFragmentToTeamDetailFragment(team)
            Navigation.findNavController(it).navigate(navDirection)
        }

    }
}