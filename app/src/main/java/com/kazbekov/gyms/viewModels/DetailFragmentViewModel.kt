package com.kazbekov.gyms.viewModels

import androidx.lifecycle.ViewModel
import com.kazbekov.gyms.data.TrainingDay

class DetailFragmentViewModel : ViewModel() {

    var trainingDay: TrainingDay? = null
        private set

    fun setTrainingDay(training: TrainingDay) {
        trainingDay = training
    }
}