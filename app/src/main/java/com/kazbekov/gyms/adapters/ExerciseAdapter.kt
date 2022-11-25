package com.kazbekov.gyms.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazbekov.gyms.R
import com.kazbekov.gyms.databinding.ItemExerciseBinding
import java.lang.NumberFormatException

class ExerciseAdapter(
    private val exerciseList: List<String>,
    private val resultsList: MutableList<Int>
) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding, resultsList)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        if (resultsList.isNotEmpty()) {
            holder.bind(exerciseList[position], resultsList[position])
        } else {
            holder.bind(exerciseList[position], 0)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    fun getResults(): List<Int> {
        return resultsList.toList()
    }

    class ExerciseViewHolder(itemBinding: ItemExerciseBinding, resultsList: MutableList<Int>) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.exerciseProgress.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    try {
                        resultsList[adapterPosition] = p0.toString().toInt()
                    } catch (e: NumberFormatException) {
                        itemBinding.exerciseProgress.error =
                            itemView.context.getString(R.string.number_format_error)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}

            })
        }

        private val exerciseName = itemBinding.exerciseName
        private val exerciseProgress = itemBinding.exerciseProgress

        fun bind(exercise: String, result: Int) {
            exerciseName.text = exercise
            exerciseProgress.text = (if (result != 0) result else "") as Editable
        }
    }
}