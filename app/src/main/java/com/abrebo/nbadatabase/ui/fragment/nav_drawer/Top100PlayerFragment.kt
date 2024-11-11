package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentTop100PlayerBinding
import com.abrebo.nbadatabase.ui.adapter.TeamDetailAdapter
import com.abrebo.nbadatabase.ui.viewmodel.FilterAndSortViewModel
import com.abrebo.nbadatabase.utils.PageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Top100PlayerFragment : Fragment() {
    private lateinit var binding:FragmentTop100PlayerBinding
    private val viewModel:FilterAndSortViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.top100Players()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentTop100PlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.players.observe(viewLifecycleOwner){
            val adapter=TeamDetailAdapter(requireContext(),it,null,viewModel,PageType.TOP_100,null)
            binding.top100PlayerRecyclerView.adapter=adapter
        }
    }

}