package com.kazbekov.gyms.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazbekov.gyms.data.TrainingDay
import com.kazbekov.gyms.data.TrainingScheme

class MainViewModel : ViewModel() {

    private val testedSchemes = listOf(
        TrainingScheme("Arms", listOf("push ups", "biceps", "shoulders")),
        TrainingScheme("Legs", listOf("sit-ups", "hips", "caviar", "load on the feet")),
    )

    private val testedList = listOf(
        TrainingDay(
            trainingNumber = 0,
            startOfTraining = System.currentTimeMillis(),
            endOfTraining = System.currentTimeMillis() + 600000,
            isFinished = false,
            trainingScheme = testedSchemes.first()
        ),

        TrainingDay(
            trainingNumber = 1,
            startOfTraining = System.currentTimeMillis(),
            isFinished = false,
            trainingScheme = testedSchemes.last(),
            resultExercises = listOf(10, 12, 5, 90)
        ),

        TrainingDay(
            trainingNumber = 2,
            startOfTraining = System.currentTimeMillis(),
            endOfTraining = System.currentTimeMillis() + 800000,
            isFinished = true,
            trainingScheme = testedSchemes.last(),
            resultExercises = listOf(122, 16, 50, 90)
        )
    )

    private val trainingsLiveData = MutableLiveData<List<TrainingDay>>(testedList)
    val trainings: LiveData<List<TrainingDay>>
        get() = trainingsLiveData

    fun updateTraining(updatedTraining: TrainingDay) {
        val index: Int =
            trainings.value?.indexOf(
                trainings.value?.find { it.trainingNumber == updatedTraining.trainingNumber }
            ) ?: error("Training list is null")
        val newList = trainings.value!!.toMutableList()
        newList[index] = updatedTraining
        trainingsLiveData.postValue(newList.toList())
    }
}