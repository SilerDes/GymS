package com.kazbekov.gyms.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazbekov.gyms.data.TrainingDay

class MainViewModel : ViewModel() {

    private val trainingsLiveData = MutableLiveData<List<TrainingDay>>(emptyList())
    val trainings: LiveData<List<TrainingDay>>
        get() = trainingsLiveData
}