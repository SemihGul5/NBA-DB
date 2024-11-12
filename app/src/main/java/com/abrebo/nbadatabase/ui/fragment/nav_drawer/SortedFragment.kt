package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentSortedBinding
import com.abrebo.nbadatabase.ui.adapter.TeamAdapter
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel
import com.abrebo.nbadatabase.utils.PageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SortedFragment : Fragment() {
    private lateinit var binding:FragmentSortedBinding
    private val viewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentSortedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sortedMenu())
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.threshold = 1
        binding.buttonApplyFilter.setOnClickListener {
            val selectedAttribute = binding.autoCompleteTextView.text.toString()

            if (sortedMenu().contains(selectedAttribute)) {
                viewModel.sortedTeamsFromAsset(selectedAttribute)
            } else {
                Toast.makeText(requireContext(), "Please select a valid attribute", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.sortedTeams.observe(viewLifecycleOwner) { sortedTeams ->
            val selectedAttribute = binding.autoCompleteTextView.text.toString()
            val adapter = TeamAdapter(requireContext(), sortedTeams,PageType.SORT_TEAMS,selectedAttribute)
            binding.recyclerViewTeams.adapter = adapter
        }

    }
    private fun sortedMenu():List<String>{
        return listOf(
            "Default",
            "Overall",
            "Inside Scoring",
            "Outside Scoring",
            "Athleticism",
            "Playmaking",
            "Defense",
            "Rebounding",
            "Intangibles",
            "Potential"
        )
    }
}