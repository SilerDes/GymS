package com.kazbekov.gyms.data

data class TrainingDay(
    val dayNumber: Int,
    val startOfTraining: Long,
    var endOfTraining: Long? = null,
    var isFinished: Boolean = false,
    val trainingScheme: TrainingScheme,
    var resultExercises: List<Int> = emptyList()
)