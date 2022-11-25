package com.kazbekov.gyms.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazbekov.gyms.R
import com.kazbekov.gyms.adapters.ExerciseAdapter
import com.kazbekov.gyms.data.TrainingDay
import com.kazbekov.gyms.databinding.FragmentDetailBinding
import com.kazbekov.gyms.viewModels.DetailFragmentViewModel

/**
 *
 * Shows detailed information about the workout(training).
 * It is possible to change the data
 *
 * */

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: DetailFragmentViewModel by viewModels()
    private var exerciseAdapter: ExerciseAdapter? = null

    private var isTrainingStateSaved = false
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        setTraining()
        initToolBar()
        bindInfo()

    }

    override fun onStart() {
        super.onStart()

        binding.finishTraining.setOnClickListener {
            viewModel.trainingDay!!.endOfTraining = System.currentTimeMillis()
            viewModel.trainingDay!!.isFinished = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        exerciseAdapter = null
    }

    private fun initToolBar() {
        binding.toolbar.setNavigationOnClickListener {
            if (!isTrainingStateSaved) saveTrainingState()
            findNavController().popBackStack()
        }
    }

    private fun saveTrainingState() {
        isTrainingStateSaved = true
        viewModel.trainingDay!!.resultExercises = exerciseAdapter!!.getResults()

        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            KEY_TRAINING_RESULT,
            viewModel.trainingDay
        ) ?: error("PreviousBackStackEntry is null")

    }

    private fun bindInfo() {
        val training = viewModel.trainingDay!!
        with(binding) {
            //bind main info
            trainingScheme.text = training.trainingScheme.name
            duration.text = if (training.endOfTraining != null) {
                // from millis to minutes
                val time = (training.endOfTraining!! - training.startOfTraining) / 60000
                time.toString()
            } else {
                "-"
            }
            statusPanel.visibility = if (training.isFinished) View.GONE else View.VISIBLE
            finishTraining.visibility = if (training.isFinished) View.GONE else View.VISIBLE

            //bind recyclerView
            exerciseAdapter = ExerciseAdapter(
                training.trainingScheme.exercises,
                training.resultExercises.toMutableList()
            )
            with(exercisesList) {
                adapter = exerciseAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun setTraining() {
        viewModel.setTrainingDay(args.training)
    }

    companion object {
        const val KEY_TRAINING_RESULT = "training_result_key"
    }
}