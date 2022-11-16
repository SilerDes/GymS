package com.kazbekov.gyms.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kazbekov.gyms.R
import com.kazbekov.gyms.databinding.FragmentMainBinding
import com.kazbekov.gyms.viewModels.MainViewModel

/**
 *
 * Contains a list of training days
 *
 * */

class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        observerLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observerLiveData() {
        mainViewModel.trainings.observe(viewLifecycleOwner) {
            TODO("process when markup will be created")
        }
    }
}