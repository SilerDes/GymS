package com.kazbekov.gyms.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazbekov.gyms.R
import com.kazbekov.gyms.adapters.TrainingAdapter
import com.kazbekov.gyms.data.TrainingDay
import com.kazbekov.gyms.databinding.FragmentMainBinding
import com.kazbekov.gyms.fragments.DetailFragment
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
    private var trainingAdapter: TrainingAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        initList()
        observeLiveData()
        observeSavedStateHandle()
    }

    override fun onStart() {
        super.onStart()

        binding.startTraining.setOnClickListener {
            startTraining()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        trainingAdapter = null
    }

    private fun observeLiveData() {
        mainViewModel.trainings.observe(viewLifecycleOwner) {
            trainingAdapter?.submitList(it)
        }
    }

    private fun observeSavedStateHandle() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<TrainingDay>(
            DetailFragment.KEY_TRAINING_RESULT
        )?.observe(viewLifecycleOwner) {
            mainViewModel.updateTraining(it)
        } ?: error("currentBackStackEntry is null")
    }

    private fun initList() {
        trainingAdapter = TrainingAdapter { onTrainingClick(it) }
        with(binding.trainingList) {
            adapter = trainingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun startTraining() {
        findNavController().navigate(R.id.action_mainFragment_to_createTrainingFragment)
    }

    private fun onTrainingClick(position: Int) {
        val training = mainViewModel.trainings.value!![position]
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(training)
        findNavController().navigate(action)
    }
}