package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentBestThreePointPlayersBinding
import com.abrebo.nbadatabase.ui.adapter.TeamDetailAdapter
import com.abrebo.nbadatabase.ui.viewmodel.FilterAndSortViewModel
import com.abrebo.nbadatabase.utils.PageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BestThreePointPlayersFragment : Fragment() {
    private lateinit var binding:FragmentBestThreePointPlayersBinding
    private val viewModel:FilterAndSortViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.sortThreePointPlayers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentBestThreePointPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.players.observe(viewLifecycleOwner){
            val adapter=TeamDetailAdapter(requireContext(),it,null,viewModel,PageType.BEST_THREE_POINT,null)
            binding.bestThreePointPlayersRecyclerView.adapter=adapter
        }
    }


}