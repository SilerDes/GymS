package com.kazbekov.gyms.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainingScheme(
    val name: String,
    val exercises: List<String>,
) : Parcelable