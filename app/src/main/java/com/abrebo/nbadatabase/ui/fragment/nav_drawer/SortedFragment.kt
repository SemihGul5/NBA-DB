package com.abrebo.nbadatabase.ui.fragment.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.abrebo.nbadatabase.R
import com.abrebo.nbadatabase.databinding.FragmentSortedBinding
import com.abrebo.nbadatabase.ui.adapter.TeamAdapter
import com.abrebo.nbadatabase.ui.viewmodel.HomeViewModel
import com.abrebo.nbadatabase.utils.PageType
import com.google.android.material.bottomsheet.BottomSheetDialog
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
        handleFilterMenuItem()
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
    private fun handleFilterMenuItem() {
        val dialog = BottomSheetDialog(requireContext())
        val bottomSheet = layoutInflater.inflate(R.layout.main_page_bottom_sheet, null)
        val listView = bottomSheet.findViewById<ListView>(R.id.listViewBottomSheet)
        val listViewAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sortedMenu())
        listView.adapter = listViewAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItemText = (parent.getItemAtPosition(position) as String)
            viewModel.sortedTeamsFromAsset(selectedItemText)
            dialog.dismiss()
            viewModel.sortedTeams.observe(viewLifecycleOwner) { sortedTeams ->
                val adapter = TeamAdapter(requireContext(), sortedTeams,PageType.SORT_TEAMS,selectedItemText)
                binding.recyclerViewTeams.adapter = adapter
            }
        }
        dialog.setContentView(bottomSheet)
        dialog.show()
    }
}