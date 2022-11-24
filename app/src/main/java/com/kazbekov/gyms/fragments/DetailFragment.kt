package com.kazbekov.gyms.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kazbekov.gyms.R
import com.kazbekov.gyms.databinding.FragmentDetailBinding

/**
 *
 * Shows detailed information about the workout.
 * It is possible to change the data
 *
 * */

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!

    private var isTrainingStateSaved = false
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        initToolBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolBar() {
        binding.toolbar.setNavigationOnClickListener {
            if (!isTrainingStateSaved) saveTrainingState()
            findNavController().popBackStack()
        }
    }

    private fun saveTrainingState() {

    }
}