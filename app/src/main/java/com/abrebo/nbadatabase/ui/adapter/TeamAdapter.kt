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
import com.abrebo.nbadatabase.ui.fragment.nav_drawer.SortedFragmentDirections
import com.abrebo.nbadatabase.utils.PageType

class TeamAdapter(val context:Context,
                  val teamItemList:List<TeamItem>,
                  val page:PageType,
                  val sortInfo:String?
):RecyclerView.Adapter<TeamAdapter.TeamHolder>() {
    inner class TeamHolder(val binding:TeamCardItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val binding=TeamCardItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return TeamHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamItemList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        val binding=holder.binding
        val team=teamItemList.get(position)
        binding.cardBackground.background=context.getDrawable(team.backgroundRes)
        binding.logoImageView.setImageResource(team.logoRes)
        binding.teamNameTextView.text=team.teamName
        if (page==PageType.SORT_TEAMS){
            when(sortInfo){
                "Overall" -> binding.sortedText.text=sortInfo+": "+team.ovr
                "Inside Scoring" -> binding.sortedText.text=sortInfo+": "+team.ins
                "Outside Scoring" -> binding.sortedText.text=sortInfo+": "+team.out
                "Playmaking" -> binding.sortedText.text=sortInfo+": "+team.pla
                "Defense" -> binding.sortedText.text=sortInfo+": "+team.def
                "Rebounding" -> binding.sortedText.text=sortInfo+": "+team.reb
                "Intangibles" -> binding.sortedText.text=sortInfo+": "+team.int
                "Potential" ->binding.sortedText.text=sortInfo+": "+team.pot
            }

        }



        binding.cardBackground.setOnClickListener {
            if (page==PageType.HOME){
                val navDirection=HomeFragmentDirections.actionHomeFragmentToTeamDetailFragment(team)
                Navigation.findNavController(it).navigate(navDirection)
            }else if (page==PageType.SORT_TEAMS){
                val navDirection=SortedFragmentDirections.actionSortedFragmentToTeamDetailFragment(team)
                Navigation.findNavController(it).navigate(navDirection)
            }

        }




    }
}